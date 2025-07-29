package com.aogdeveloper.banconova.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private BigDecimal dailyTransferLimit;
    private Long customerId;
    private LocalDateTime createdAt;
    private boolean active;

    public enum AccountType {
        SAVINGS, CHECKING, BUSINESS
    }

    @Builder
    public Account(Long id,
            String accountNumber,
            AccountType type,
            BigDecimal balance,
            BigDecimal dailyTransferLimit,
            Long customerId,
            LocalDateTime createdAt,
            boolean active) {

        if (balance == null
                || balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("balance cannot be negative");
        }
        if (dailyTransferLimit == null
                || dailyTransferLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("daily transfer limit cannot be negative");
        }
        if (accountNumber == null
                || accountNumber.isBlank()) {
            throw new IllegalArgumentException("account number is mandatory");
        }
        this.id = id;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
        this.dailyTransferLimit = dailyTransferLimit;
        this.customerId = customerId;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.active = active;
    }

    // Business rules
    public boolean canWithdraw(BigDecimal amount) {
        return active && balance.compareTo(amount) >= 0;
    }

    public boolean canTransfer(BigDecimal amount) {
        return canWithdraw(amount) && dailyTransferLimit.compareTo(amount) >= 0;
    }

    public synchronized void withdraw(BigDecimal amount) {

        if (!canWithdraw(amount)) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }

    public synchronized void deposit(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }
}
