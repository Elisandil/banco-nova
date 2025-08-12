package com.aogdeveloper.banconova.infrastructure.persistence.entities;

import com.aogdeveloper.banconova.domain.enums.TransferType;
import com.aogdeveloper.banconova.domain.enums.TransferStatus;
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
@Table(name = "transfers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "source_account_id", nullable = false)
    private Long sourceAccountId;
    
    @Column(name = "destination_account_id")
    private Long destinationAccountId;
    
    @Column(name = "destination_account_number", length = 20)
    private String destinationAccountNumber;
    
    @Column(name = "destination_bank_code", length = 10)
    private String destinationBankCode;
    
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private java.math.BigDecimal amount;
    
    @Column(name = "concept", length = 255)
    private String concept;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransferType type;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransferStatus status;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "processed_at")
    private LocalDateTime processedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
