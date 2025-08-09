package com.aogdeveloper.banconova.application.dtos.auth;

import com.aogdeveloper.banconova.application.dtos.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String tokenType; //= "Bearer";
    private Long expiresIn;
    private CustomerDTO customer;
}
