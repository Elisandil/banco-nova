package com.aogdeveloper.banconova.infrastructure.persistence.entities;

import com.aogdeveloper.banconova.domain.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "account_number", 
            unique = true, 
            nullable = false, 
            length = 20)
    private String accountNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccountType type;
    
    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private java.math.BigDecimal balance;
    
    @Column(name = "daily_transfer_limit", 
            nullable = false, 
            precision = 15, 
            scale = 2)
    private java.math.BigDecimal dailyTransferLimit;
    
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
