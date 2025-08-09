package com.aogdeveloper.banconova.application.dtos.account;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDTO {
    @NotNull(message = "Account type is required")
    private String type; // SAVINGS, CHECKING, BUSINESS
}
