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
public class Transfer {
    private Long id;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private String destinationAccountNumber;
    private String destinationBankCode;
    private BigDecimal amount;
    private String concept;
    private TransferType type;
    private TransferStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    
    
    public enum TransferType {
        OWN_ACCOUNTS, THIRD_PARTY_SAME_BANK, THIRD_PARTY_OTHER_BANK
    }
    
    public enum TransferStatus {
        PENDING, PROCESSING, COMPLETED, FAILED, CANCELLED
    }
    
    // Business Rules
    public boolean isOwnAccountTransfer() {
        return type == TransferType.OWN_ACCOUNTS;
    }
    
    public boolean requiresBeneficiaryValidation() {
        return type == TransferType.THIRD_PARTY_SAME_BANK ||
                type == TransferType.THIRD_PARTY_OTHER_BANK;
    }
}
