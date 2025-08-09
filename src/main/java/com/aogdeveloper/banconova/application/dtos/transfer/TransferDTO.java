package com.aogdeveloper.banconova.application.dtos.transfer;

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
public class TransferDTO {
    private Long id;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private String destinationAccountNumber;
    private String destinationBankCode;
    private BigDecimal amount;
    private String concept;
    private String type;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}
