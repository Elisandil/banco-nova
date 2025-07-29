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
public class Payment {
    private Long id;
    private Long accountId;
    private PaymentType type;
    private String payCode;
    private String payName;
    private String reference;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    
    
    public enum PaymentType {
        UTILITY_BILL, CREDIT_CARD, LOAN, MOBILE_RECHARGE, SERVICE
    }
    
    public enum PaymentStatus {
        PENDING, PROCESSING, COMPLETED, FAILED, CANCELLED
    }
    
    // Business Rules
    public boolean requiresReference() {
        return type == PaymentType.UTILITY_BILL ||
                type == PaymentType.CREDIT_CARD;
    }
}
