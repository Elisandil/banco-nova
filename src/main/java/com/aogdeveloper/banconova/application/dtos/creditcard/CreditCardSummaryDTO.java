package com.aogdeveloper.banconova.application.dtos.creditcard;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardSummaryDTO {
    private Long id;
    private String maskedNumber;
    private BigDecimal availableCredit;
    private BigDecimal currentBalance;
    private String status;
}
