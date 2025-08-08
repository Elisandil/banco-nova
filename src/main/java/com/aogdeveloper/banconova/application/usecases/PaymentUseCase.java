package com.aogdeveloper.banconova.application.usecases;

import com.aogdeveloper.banconova.domain.entities.Account;
import com.aogdeveloper.banconova.domain.entities.Payment;
import com.aogdeveloper.banconova.domain.entities.Transaction;
import com.aogdeveloper.banconova.domain.repositories.AccountRepository;
import com.aogdeveloper.banconova.domain.repositories.PaymentRepository;
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
public class PaymentUseCase {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    
    @Transactional
    public Payment makePayment(
            Long accountId, 
            Payment.PaymentType type, 
            String payCode, 
            String payName,
            String reference, 
            BigDecimal amount) {
        
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        
        if (accountOpt.isEmpty()) {
            throw new IllegalArgumentException("Account not found");
        }
        Account account = accountOpt.get();
        
        if (!account.canWithdraw(amount)) {
            throw new IllegalArgumentException(
                    "Insufficient funds or account inactive");
        }

        Payment.PaymentType[] typesRequiringReference = {
            Payment.PaymentType.UTILITY_BILL, 
            Payment.PaymentType.CREDIT_CARD
        };
        boolean requiresRef = java.util.Arrays.asList(typesRequiringReference)
                .contains(type);
        
        if (requiresRef && (reference == null || reference.trim().isEmpty())) {
            throw new IllegalArgumentException(
                    "Reference is required for this payment type");
        }
        account.withdraw(amount);
        accountRepository.save(account);
        
        Payment payment = Payment.builder()
                .accountId(accountId)
                .type(type)
                .payCode(payCode)
                .payName(payName)
                .reference(reference)
                .amount(amount)
                .status(Payment.PaymentStatus.PROCESSING)
                .createdAt(LocalDateTime.now())
                .build();
        
        payment = paymentRepository.save(payment);

        Transaction transaction = Transaction.builder()
                .accountId(accountId)
                .type(Transaction.TransactionType.PAYMENT)
                .amount(amount)
                .description("Payment to " + payName)
                .reference("PAY-" + payment.getId())
                .createdAt(LocalDateTime.now())
                .status(Transaction.TransactionStatus.COMPLETED)
                .build();
        
        transactionRepository.save(transaction);
        
        return payment;
    }
    
    public List<Payment> getAccountPayments(Long accountId) {
        return paymentRepository.findByAccountIdOrderByCreatedAtDesc(accountId);
    }
    
    public List<Payment> getPaymentsByType(
            Long accountId, 
            Payment.PaymentType type) {
        return paymentRepository.findByAccountIdAndType(accountId, type);
    }
}