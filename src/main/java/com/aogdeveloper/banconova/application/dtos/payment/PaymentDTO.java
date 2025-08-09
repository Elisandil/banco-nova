package com.aogdeveloper.banconova.application.dtos.payment;

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
public class PaymentDTO {
    private Long id;
    private Long accountId;
    private String type;
    private String payeeCode;
    private String payeeName;
    private String reference;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}
