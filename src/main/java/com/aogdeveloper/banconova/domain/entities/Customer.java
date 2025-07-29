package com.aogdeveloper.banconova.domain.entities;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String documentNumber;
    private String documentType;
    private String firstName;
    private String lastName;
    private String lastLastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private boolean active;
    
    // Business rules
    public String getFullName() {
        
        if (lastLastName != null) {
            return firstName + " " + lastName + " " + lastLastName;
        }
        else {
            return firstName + " " + lastName;
        }
    }
    
    public boolean canCreateAccount() {
        return active && documentNumber != null && !documentNumber.isEmpty();
    }
}
