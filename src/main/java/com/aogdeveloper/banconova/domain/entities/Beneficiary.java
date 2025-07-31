package com.aogdeveloper.banconova.domain.entities;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {
    private Long id;
    private Long customerId;
    private String accountNumber;
    private String accountHolderName;
    private String bankCode;
    private String bankName;
    private String alias;
    private BeneficiaryType type;
    private boolean verified;
    private LocalDateTime createdAt;
    
    
    public enum BeneficiaryType {
        SAME_BANK, OTHER_BANK
    }
    
    // Business Rules
    public boolean canReceiveTransfer() {
        return verified && 
                accountNumber != null && 
                !accountNumber.isEmpty();
    }
    
    public String getDisplayName() {
        return alias != null && 
                alias.isEmpty() ? 
                    alias : 
                    accountHolderName;
    }
}
