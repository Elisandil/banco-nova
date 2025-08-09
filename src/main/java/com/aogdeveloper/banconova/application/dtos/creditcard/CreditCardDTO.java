package com.aogdeveloper.banconova.application.dtos.creditcard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {
    private Long id;
    private String maskedNumber;
    private Long customerId;
    private BigDecimal creditLimit;
    private BigDecimal availableCredit;
    private BigDecimal currentBalance;
    private LocalDate expiryDate;
    private String status;
    private LocalDateTime createdAt;
}
