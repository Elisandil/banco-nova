package com.aogdeveloper.banconova.domain.enums;

public enum TransactionStatus {
    PENDING("Pendiente"),
    COMPLETED("Completada"),
    FAILED("Fallida"),
    CANCELLED("Cancelada");
    
    private final String displayName;
    
    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean allowsModification() {
        return this == PENDING;
    }

    public boolean isFinal() {
        return this == COMPLETED || this == FAILED || this == CANCELLED;
    }

    public boolean isSuccessful() {
        return this == COMPLETED;
    }
}
