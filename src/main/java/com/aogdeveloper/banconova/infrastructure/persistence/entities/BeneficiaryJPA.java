package com.aogdeveloper.banconova.infrastructure.persistence.entities;

import com.aogdeveloper.banconova.domain.enums.BeneficiaryType;
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
@Table(name = "beneficiaries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    
    @Column(name = "account_number", nullable = false, length = 20)
    private String accountNumber;
    
    @Column(name = "account_holder_name", nullable = false, length = 100)
    private String accountHolderName;
    
    @Column(name = "bank_code", length = 10)
    private String bankCode;
    
    @Column(name = "bank_name", length = 100)
    private String bankName;
    
    @Column(name = "alias", length = 50)
    private String alias;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private BeneficiaryType type;
    
    @Column(name = "verified", nullable = false)
    private boolean verified;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
