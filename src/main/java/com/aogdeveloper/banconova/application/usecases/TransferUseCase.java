package com.aogdeveloper.banconova.application.usecases;

import com.aogdeveloper.banconova.domain.entities.Account;
import com.aogdeveloper.banconova.domain.entities.Beneficiary;
import com.aogdeveloper.banconova.domain.entities.Transaction;
import com.aogdeveloper.banconova.domain.entities.Transfer;
import com.aogdeveloper.banconova.domain.repositories.AccountRepository;
import com.aogdeveloper.banconova.domain.repositories.BeneficiaryRepository;
import com.aogdeveloper.banconova.domain.repositories.TransactionRepository;
import com.aogdeveloper.banconova.domain.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferUseCase {
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    
    @Transactional
    public Transfer transferBetweenOwnAccounts(
            Long sourceAccountId, 
            Long destinationAccountId, 
            BigDecimal amount, 
            String concept) {

        Optional<Account> sourceOpt = accountRepository
                .findById(sourceAccountId);
        Optional<Account> destOpt = accountRepository
                .findById(destinationAccountId);
        
        if (sourceOpt.isEmpty() || destOpt.isEmpty()) {
            throw new IllegalArgumentException(
                    "One or both accounts not found");
        }
        Account sourceAccount = sourceOpt.get();
        Account destAccount = destOpt.get();
        
        if (!sourceAccount.getCustomerId().equals(destAccount.getCustomerId())) {
            throw new IllegalArgumentException(
                    "Accounts must belong to same customer");
        }
        if (!sourceAccount.canTransfer(amount)) {
            throw new IllegalArgumentException(
                    "Transfer not allowed - insufficient funds or limit exceeded");
        }
        sourceAccount.withdraw(amount);
        destAccount.deposit(amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(destAccount);

        Transfer transfer = Transfer.builder()
                .sourceAccountId(sourceAccountId)
                .destinationAccountId(destinationAccountId)
                .destinationAccountNumber(destAccount.getAccountNumber())
                .amount(amount)
                .concept(concept)
                .type(Transfer.TransferType.OWN_ACCOUNTS)
                .status(Transfer.TransferStatus.COMPLETED)
                .createdAt(LocalDateTime.now())
                .processedAt(LocalDateTime.now())
                .build();
        
        transfer = transferRepository.save(transfer);
        createTransferTransactions(sourceAccountId, 
                destinationAccountId, amount, transfer.getId());
        
        return transfer;
    }
    
    @Transactional
    public Transfer transferToThirdParty(
            Long sourceAccountId, 
            String destinationAccountNumber, 
            BigDecimal amount, 
            String concept) {
        
        Optional<Account> sourceOpt = accountRepository
                .findById(sourceAccountId);
        
        if (sourceOpt.isEmpty()) {
            throw new IllegalArgumentException("Source account not found");
        }
        Account sourceAccount = sourceOpt.get();
        
        if (!sourceAccount.canTransfer(amount)) {
            throw new IllegalArgumentException("Transfer not allowed");
        }
        Optional<Beneficiary> beneficiaryOpt = beneficiaryRepository
                .findByCustomerIdAndAccountNumber(sourceAccount.getCustomerId(),
                        destinationAccountNumber);
        
        if (beneficiaryOpt.isEmpty() || !beneficiaryOpt.get()
                .canReceiveTransfer()) {
            throw new IllegalArgumentException(
                    "Beneficiary not found or not verified");
        }
        sourceAccount.withdraw(amount);
        accountRepository.save(sourceAccount);
        
        Transfer transfer = Transfer.builder()
                .sourceAccountId(sourceAccountId)
                .destinationAccountNumber(destinationAccountNumber)
                .amount(amount)
                .concept(concept)
                .type(Transfer.TransferType.THIRD_PARTY_SAME_BANK)
                .status(Transfer.TransferStatus.PROCESSING)
                .createdAt(LocalDateTime.now())
                .build();
        
        transfer = transferRepository.save(transfer);

        Transaction outTransaction = Transaction.builder()
                .accountId(sourceAccountId)
                .type(Transaction.TransactionType.TRANSFER_OUT)
                .amount(amount)
                .description("Transfer to " + destinationAccountNumber)
                .reference("TRF-" + transfer.getId())
                .createdAt(LocalDateTime.now())
                .status(Transaction.TransactionStatus.COMPLETED)
                .build();
        
        transactionRepository.save(outTransaction);
        
        return transfer;
    }
    
    public List<Transfer> getAccountTransfers(Long accountId) {
        return transferRepository
                .findBySourceAccountIdOrderByCreatedAtDesc(accountId);
    }
    
    private void createTransferTransactions(
            Long sourceId, 
            Long destId, 
            BigDecimal amount, 
            Long transferId) {
        String reference = "TRF-" + transferId;
        
        Transaction outTransaction = Transaction.builder()
                .accountId(sourceId)
                .type(Transaction.TransactionType.TRANSFER_OUT)
                .amount(amount)
                .description("Transfer out")
                .reference(reference)
                .createdAt(LocalDateTime.now())
                .status(Transaction.TransactionStatus.COMPLETED)
                .build();
        
        Transaction inTransaction = Transaction.builder()
                .accountId(destId)
                .type(Transaction.TransactionType.TRANSFER_IN)
                .amount(amount)
                .description("Transfer in")
                .reference(reference)
                .createdAt(LocalDateTime.now())
                .status(Transaction.TransactionStatus.COMPLETED)
                .build();
        
        transactionRepository.save(outTransaction);
        transactionRepository.save(inTransaction);
    }
}
