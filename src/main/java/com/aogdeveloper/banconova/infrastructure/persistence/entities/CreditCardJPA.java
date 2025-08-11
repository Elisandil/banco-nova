package com.aogdeveloper.banconova.infrastructure.persistence.entities;

import com.aogdeveloper.banconova.domain.enums.CardStatus;
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
@Table(name = "credit_cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "card_number", unique = true, nullable = false, length = 20)
    private String cardNumber;
    
    @Column(name = "masked_number", nullable = false, length = 20)
    private String maskedNumber;
    
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    
    @Column(name = "credit_limit", 
            nullable = false, 
            precision = 15, 
            scale = 2)
    private java.math.BigDecimal creditLimit;
    
    @Column(name = "available_credit", 
            nullable = false, 
            precision = 15, 
            scale = 2)
    private java.math.BigDecimal availableCredit;
    
    @Column(name = "current_balance", 
            nullable = false, 
            precision = 15, 
            scale = 2)
    private java.math.BigDecimal currentBalance;
    
    @Column(name = "expiry_date", nullable = false)
    private java.time.LocalDate expiryDate;
    
    @Column(name = "cvv", nullable = false, length = 5)
    private String cvv;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CardStatus status;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
