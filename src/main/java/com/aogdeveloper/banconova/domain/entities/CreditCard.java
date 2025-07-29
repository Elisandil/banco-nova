package com.aogdeveloper.banconova.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private Long id;
    private String cardNumber;
    private String maskedNumber;
    private Long customerId;
    private BigDecimal creditLimit;
    private BigDecimal availableCredit;
    private BigDecimal currentBalance;
    private LocalDate expiryDate;
    private String cvv;
    private CardStatus status;
    private LocalDateTime createdAt;
    
    public enum CardStatus {
        ACTIVE, BLOCKED, CANCELLED, EXPIRED
    }
    
    // Business rules
    public boolean canMakePayment(BigDecimal amount) {
        return status == CardStatus.ACTIVE && 
               availableCredit.compareTo(amount) >= 0 &&
               !isExpired();
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    
    public void makePayment(BigDecimal amount) {
        
        if (!canMakePayment(amount)) {
            throw new IllegalArgumentException("Payment not allowed");
        }
        this.availableCredit = this.availableCredit.subtract(amount);
        this.currentBalance = this.currentBalance.add(amount);
    }

    public boolean isValidCvvFormat() {
        return isValidCvvFormat(this.cvv);
    }
    
    private boolean isValidCvvFormat(String cvv) {
        return cvv != null && cvv.matches("\\d{3,4}");
    }
    
    private String generateMaskedNumber(String cardNumber) {
        
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return "****";
        }
        String cleanNumber = cardNumber.replaceAll("[^0-9]", "");
        
        if (cleanNumber.length() < 4) {
            return "****";
        }
        String lastFour = cleanNumber.substring(cleanNumber.length() - 4);
        
        return switch (cleanNumber.length()) {
            case 16 -> "**** **** **** " + lastFour;
            case 15 -> "**** ****** *" + lastFour.substring(1);
            default -> "****" + lastFour;
        };
    }  
    
    public String getMaskedCardNumber() {
        return generateMaskedNumber(this.cardNumber);
    }    
}
