package com.aogdeveloper.banconova.domain.repositories.usecases;

import com.aogdeveloper.banconova.domain.entities.Account;
import com.aogdeveloper.banconova.domain.entities.Transaction;
import com.aogdeveloper.banconova.domain.repositories.AccountRepository;
import com.aogdeveloper.banconova.domain.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionUseCase {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    
    @Transactional
    public Transaction deposit(Long accountId, BigDecimal amount, String description) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        
        if (accountOpt.isEmpty()) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accountOpt.get();
        account.deposit(amount);
        accountRepository.save(account);
        
        Transaction transaction = Transaction.builder()
                .accountId(accountId)
                .type(Transaction.TransactionType.DEPOSIT)
                .amount(amount)
                .description(description)
                .reference(generateReference())
                .createdAt(LocalDateTime.now())
                .status(Transaction.TransactionStatus.COMPLETED)
                .build();
                
        return transactionRepository.save(transaction);
    }
    
    @Transactional
    public Transaction withdraw(
            Long accountId, 
            BigDecimal amount, 
            String description) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        
        if (accountOpt.isEmpty()) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accountOpt.get();
        
        if (!account.canWithdraw(amount)) {
            throw new IllegalArgumentException(
                    "Insufficient funds or account inactive");
        }
        account.withdraw(amount);
        accountRepository.save(account);
        
        Transaction transaction = Transaction.builder()
                .accountId(accountId)
                .type(Transaction.TransactionType.WITHDRAWAL)
                .amount(amount)
                .description(description)
                .reference(generateReference())
                .createdAt(LocalDateTime.now())
                .status(Transaction.TransactionStatus.COMPLETED)
                .build();
                
        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> getAccountTransactions(Long accountId) {
        return transactionRepository.findByAccIDOrderByCreatedAtDesc(accountId);
    }
    
    public List<Transaction> getAccountTransactionsByDateRange(
            Long accountId, 
            LocalDateTime startDate, 
            LocalDateTime endDate) {
        
        return transactionRepository
                .findByAccIDAndCreatedAtBetweenOrderByCreatedAtDesc(
                accountId, startDate, endDate);
    }
    
    private String generateReference() {
        return "TXN-" + System.currentTimeMillis();
    }
}
