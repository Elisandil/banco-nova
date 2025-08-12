package com.aogdeveloper.banconova.domain.enums;

import java.math.BigDecimal;

public enum PaymentType {
    UTILITY_BILL("Factura de Servicios", true),
    CREDIT_CARD("Tarjeta de Crédito", true),
    LOAN("Préstamo", false),
    MOBILE_RECHARGE("Recarga Móvil", false),
    SERVICE("Servicio", false);
    
    private final String displayName;
    private final boolean requiresReference;
    
    PaymentType(String displayName, boolean requiresReference) {
        this.displayName = displayName;
        this.requiresReference = requiresReference;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresReference() {
        return requiresReference;
    }

    public BigDecimal getBaseFee() {
        return switch (this) {
            case UTILITY_BILL -> new BigDecimal("2.00");
            case CREDIT_CARD -> new BigDecimal("5.00");
            case LOAN -> new BigDecimal("3.00");
            case MOBILE_RECHARGE -> new BigDecimal("1.00");
            case SERVICE -> new BigDecimal("2.50");
        };
    }

    public boolean hasSpecialLimits() {
        return this == MOBILE_RECHARGE;
    }
}
