package com.aogdeveloper.banconova.application.dtos.account;

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
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private String type;
    private BigDecimal balance;
    private BigDecimal dailyTransferLimit;
    private Long customerId;
    private LocalDateTime createdAt;
    private boolean active;
}
