package com.aogdeveloper.banconova.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "customers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "document_number", 
            unique = true, 
            nullable = false, 
            length = 20)
    private String documentNumber;
    
    @Column(name = "document_type", nullable = false, length = 10)
    private String documentType;
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Column(name = "last_last_name", nullable = true, length = 50)
    private String lastLastName;
    
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
