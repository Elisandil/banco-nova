package com.aogdeveloper.banconova.application.dtos.common;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private boolean success; // = false;
    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;
    private String path;
}
