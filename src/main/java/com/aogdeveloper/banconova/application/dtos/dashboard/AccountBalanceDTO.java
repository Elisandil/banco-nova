package com.aogdeveloper.banconova.application.dtos.dashboard;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceDTO {
    private Long accountId;
    private String accountNumber;
    private String type;
    private BigDecimal balance;
    private BigDecimal availableBalance;
}
