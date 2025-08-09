package com.aogdeveloper.banconova.application.dtos.beneficiary;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryDTO {
    private Long id;
    private Long customerId;
    private String accountNumber;
    private String accountHolderName;
    private String bankCode;
    private String bankName;
    private String alias;
    private String type;
    private boolean verified;
    private LocalDateTime createdAt;
    private String displayName;
}
