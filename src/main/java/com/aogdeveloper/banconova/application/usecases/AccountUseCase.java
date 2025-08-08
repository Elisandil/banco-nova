package com.aogdeveloper.banconova.application.usecases;

import com.aogdeveloper.banconova.domain.entities.Account;
import com.aogdeveloper.banconova.domain.entities.Customer;
import com.aogdeveloper.banconova.domain.repositories.AccountRepository;
import com.aogdeveloper.banconova.domain.repositories.CustomerRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountUseCase {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private static final BigDecimal DEFAULT_DAILY_LIMIT = 
            new BigDecimal("10000.00");
    private static final int MAX_ACCOUNTS_PER_CUSTOMER = 5;
    
    public Account createAccount(Long customerId, Account.AccountType type) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        
        if (customer.isEmpty() || !customer.get().canCreateAccount()) {
            throw new IllegalArgumentException(
                    "Customer cannot create account");
        }
        long accountCount = accountRepository.countByCustomerID(customerId);
        
        if (accountCount >= MAX_ACCOUNTS_PER_CUSTOMER) {
            throw new IllegalArgumentException(
                    "Maximum number of accounts reached");
        }
        String accountNumber = generateAccountNumber();
        
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .type(type)
                .balance(BigDecimal.ZERO)
                .dailyTransferLimit(DEFAULT_DAILY_LIMIT)
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .active(true)
                .build();
                
        return accountRepository.save(account);
    }
    
    public List<Account> getCustomerAccounts(Long customerId) {
        return accountRepository.findByActiveAndCustomerID(true, customerId);
    }
    
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }
    
    public Optional<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    public Account updateDailyLimit(Long accountId, BigDecimal newLimit) {
        Optional<Account> account = accountRepository.findById(accountId);
        
        if (account.isEmpty()) {
            throw new IllegalArgumentException("Account not found");
        }
        Account accountToUpdate = account.get();
        accountToUpdate.setDailyTransferLimit(newLimit);
        
        return accountRepository.save(accountToUpdate);
    }
    
    private String generateAccountNumber() {
        String accountNumber;
        
        do {
            accountNumber = "ACC" + System.currentTimeMillis() + 
                          String.format("%04d", (int)(Math.random() * 10000));
        } while (accountRepository.existsByAccountNumber(accountNumber));
        
        return accountNumber;
    }
}

