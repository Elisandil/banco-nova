package com.aogdeveloper.banconova.application.dtos.transaction;

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
public class TransactionDTO {
    private Long id;
    private Long accountId;
    private String type;
    private BigDecimal amount;
    private String description;
    private String reference;
    private LocalDateTime createdAt;
    private String status;
}
