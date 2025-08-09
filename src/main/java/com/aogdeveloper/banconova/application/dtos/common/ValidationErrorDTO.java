package com.aogdeveloper.banconova.application.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDTO {
    private String field;
    private String message;
    private Object rejectedValue;
}
