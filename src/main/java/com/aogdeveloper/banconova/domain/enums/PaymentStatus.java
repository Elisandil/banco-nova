package com.aogdeveloper.banconova.domain.enums;

public enum PaymentStatus {
    PENDING("Pendiente"),
    PROCESSING("Procesando"),
    COMPLETED("Completado"),
    FAILED("Fallido"),
    CANCELLED("Cancelado");
    
    private final String displayName;
    
    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean canBeCancelled() {
        return this == PENDING || this == PROCESSING;
    }

    public boolean isInProgress() {
        return this == PENDING || this == PROCESSING;
    }

    public boolean isSuccessful() {
        return this == COMPLETED;
    }

    public boolean requiresNotification() {
        return this == COMPLETED || this == FAILED;
    }
}
