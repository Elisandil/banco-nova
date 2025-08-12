package com.aogdeveloper.banconova.domain.enums;

public enum CardStatus {
    ACTIVE("Activa"),
    BLOCKED("Bloqueada"),
    CANCELLED("Cancelada"),
    EXPIRED("Expirada");
    
    private final String displayName;
    
    CardStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean canMakePayments() {
        return this == ACTIVE;
    }

    public boolean canBeUnblocked() {
        return this == BLOCKED;
    }

    public boolean isPermanent() {
        return this == CANCELLED || this == EXPIRED;
    }

    public boolean requiresRenewal() {
        return this == EXPIRED;
    }
}
