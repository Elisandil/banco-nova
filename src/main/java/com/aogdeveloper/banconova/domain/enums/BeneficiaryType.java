package com.aogdeveloper.banconova.domain.enums;

public enum BeneficiaryType {
    SAME_BANK("Mismo Banco"),
    OTHER_BANK("Otro Banco");
    
    private final String displayName;
    
    BeneficiaryType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresBankCode() {
        return this == OTHER_BANK;
    }

    public int getVerificationTimeHours() {
        return switch (this) {
            case SAME_BANK -> 1;
            case OTHER_BANK -> 24;
        };
    }

    public boolean requiresManualVerification() {
        return this == OTHER_BANK;
    }
}
