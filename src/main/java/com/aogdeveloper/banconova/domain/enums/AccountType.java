package com.aogdeveloper.banconova.domain.enums;

import java.math.BigDecimal;

public enum AccountType {
    SAVINGS("Cuenta de Ahorros"),
    CHECKING("Cuenta Corriente"), 
    BUSINESS("Cuenta Empresarial");
    
    private final String displayName;
    
    AccountType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public boolean allowsOverdraft() {
        return this == CHECKING || this == BUSINESS;
    }

    public BigDecimal getMinimumBalance() {
        return switch (this) {
            case SAVINGS -> new BigDecimal("100.00");
            case CHECKING -> BigDecimal.ZERO;
            case BUSINESS -> new BigDecimal("1000.00");
        };
    }

    public boolean hasMonthlyFee() {
        return this == BUSINESS;
    }

    public BigDecimal getMonthlyFee() {
        return switch (this) {
            case SAVINGS, CHECKING -> BigDecimal.ZERO;
            case BUSINESS -> new BigDecimal("25.00");
        };
    }
}
