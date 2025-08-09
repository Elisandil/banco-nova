package com.aogdeveloper.banconova.application.dtos.beneficiary;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBeneficiaryRequestDTO {
    @NotBlank(message = "Account number is required")
    private String accountNumber;
    
    @NotBlank(message = "Account holder name is required")
    private String accountHolderName;
    
    private String bankCode;
    private String bankName;
    private String alias;
    
    @NotBlank(message = "Beneficiary type is required")
    private String type; // SAME_BANK, OTHER_BANK
}
