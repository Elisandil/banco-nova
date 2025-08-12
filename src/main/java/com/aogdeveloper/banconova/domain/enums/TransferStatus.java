package com.aogdeveloper.banconova.domain.enums;

public enum TransferStatus {
    PENDING("Pendiente"),
    PROCESSING("Procesando"),
    COMPLETED("Completada"),
    FAILED("Fallida"),
    CANCELLED("Cancelada");
    
    private final String displayName;
    
    TransferStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean canBeCancelled() {
        return this == PENDING;
    }

    public boolean isInProgress() {
        return this == PENDING || this == PROCESSING;
    }

    public boolean isSuccessful() {
        return this == COMPLETED;
    }

    public TransferStatus getNextStatus() {
        return switch (this) {
            case PENDING -> PROCESSING;
            case PROCESSING -> COMPLETED;
            case COMPLETED, FAILED, CANCELLED -> this;
        };
    }
}
