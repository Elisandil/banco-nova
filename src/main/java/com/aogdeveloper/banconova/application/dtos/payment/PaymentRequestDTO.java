package com.aogdeveloper.banconova.application.dtos.payment;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    @NotBlank(message = "Payment type is required")
    private String type; // UTILITY_BILL, CREDIT_CARD, LOAN, 
                         // MOBILE_RECHARGE, SERVICE
    
    @NotBlank(message = "Payee code is required")
    private String payeeCode;
    
    @NotBlank(message = "Payee name is required")
    private String payeeName;
    
    private String reference;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
}
