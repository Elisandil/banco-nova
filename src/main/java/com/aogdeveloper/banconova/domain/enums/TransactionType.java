package com.aogdeveloper.banconova.domain.enums;

public enum TransactionType {
    DEPOSIT("Depósito"),
    WITHDRAWAL("Retiro"),
    TRANSFER_OUT("Transferencia Saliente"),
    TRANSFER_IN("Transferencia Entrante"),
    PAYMENT("Pago"),
    FEE("Comisión");
    
    private final String displayName;
    
    TransactionType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean isDebit() {
        return this == WITHDRAWAL ||
               this == TRANSFER_OUT ||
               this == PAYMENT ||
               this == FEE;
    }

    public boolean isCredit() {
        return this == DEPOSIT ||
               this == TRANSFER_IN;
    }

    public boolean requiresValidation() {
        return this == TRANSFER_OUT || this == PAYMENT;
    }
}
