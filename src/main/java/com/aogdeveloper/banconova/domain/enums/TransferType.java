package com.aogdeveloper.banconova.domain.enums;

import java.math.BigDecimal;

public enum TransferType {
    OWN_ACCOUNTS("Entre Cuentas Propias"),
    THIRD_PARTY_SAME_BANK("Terceros Mismo Banco"),
    THIRD_PARTY_OTHER_BANK("Terceros Otro Banco");
    
    private final String displayName;
    
    TransferType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public boolean isOwnAccountTransfer() {
        return this == OWN_ACCOUNTS;
    }

    public boolean requiresBeneficiaryValidation() {
        return this == THIRD_PARTY_SAME_BANK || this == THIRD_PARTY_OTHER_BANK;
    }

    public BigDecimal getFee() {
        return switch (this) {
            case OWN_ACCOUNTS -> BigDecimal.ZERO;
            case THIRD_PARTY_SAME_BANK -> new BigDecimal("3.00");
            case THIRD_PARTY_OTHER_BANK -> new BigDecimal("8.00");
        };
    }

    public boolean isImmediate() {
        return this == OWN_ACCOUNTS || this == THIRD_PARTY_SAME_BANK;
    }

    public int getProcessingTimeHours() {
        return switch (this) {
            case OWN_ACCOUNTS -> 0;
            case THIRD_PARTY_SAME_BANK -> 1;
            case THIRD_PARTY_OTHER_BANK -> 24;
        };
    }
}
