package com.aogdeveloper.banconova.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private Long accountId;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private String reference;
    private LocalDateTime createdAt;
    private TransactionStatus status;
    
    
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_OUT, TRANSFER_IN, PAYMENT, FEE
    }
    
    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }
    
    // Business Rules
    public boolean isDebit() {
        return type == TransactionType.WITHDRAWAL ||
                type == TransactionType.TRANSFER_OUT ||
                type == TransactionType.PAYMENT ||
                type == TransactionType.FEE;
    }
    
    public boolean isCredit() {
        return type == TransactionType.DEPOSIT ||
                type == TransactionType.TRANSFER_IN;
    }
}
