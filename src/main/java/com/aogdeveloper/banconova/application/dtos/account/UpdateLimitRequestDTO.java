package com.aogdeveloper.banconova.application.dtos.account;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLimitRequestDTO {
    @NotNull(message = "New limit is required")
    @DecimalMin(value = "0.01", message = "Limit must be greater than 0")
    private BigDecimal newLimit;
}
