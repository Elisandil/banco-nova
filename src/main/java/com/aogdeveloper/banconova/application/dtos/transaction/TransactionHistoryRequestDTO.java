package com.aogdeveloper.banconova.application.dtos.transaction;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryRequestDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String type; // Optional filter by transaction type
}
