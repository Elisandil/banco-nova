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
public class CustomerAuth {
    private Long id;
    private Long customerId;
    private String email;
    private String passwordHash;
    private boolean enabled;
    private int failedAttempts;
    private LocalDateTime lastLogin;
    private LocalDateTime lockedUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Business rules
    public boolean isAccountLocked() {
        return lockedUntil != null && LocalDateTime.now().isBefore(lockedUntil);
    }
    
    public boolean canAttemptLogin() {
        return enabled && !isAccountLocked();
    }
    
    public void lockAccount(int lockMinutes) {
        this.lockedUntil = LocalDateTime.now().plusMinutes(lockMinutes);
    }
    
    public void resetFailedAttempts() {
        this.failedAttempts = 0;
        this.lockedUntil = null;
    }
    
    public void incrementFailedAttempts() {
        this.failedAttempts++;
    }
}
