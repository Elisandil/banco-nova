package com.aogdeveloper.banconova.application.dtos.account;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSummaryDTO {
    private Long id;
    private String accountNumber;
    private String type;
    private BigDecimal balance;
    private String maskedAccountNumber;
}
