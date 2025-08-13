package com.aogdeveloper.banconova.application.mappers;

import com.aogdeveloper.banconova.application.dtos.account.AccountDTO;
import com.aogdeveloper.banconova.application.dtos.account.AccountSummaryDTO;
import com.aogdeveloper.banconova.domain.entities.Account;
import com.aogdeveloper.banconova.domain.enums.AccountType;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements DTOMapper<Account, AccountDTO> {
    
    @Override
    public AccountDTO toDTO(Account account) {
        
        if (account == null) return null;
        
        return AccountDTO.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .type(account.getType().name())
                .balance(account.getBalance())
                .dailyTransferLimit(account.getDailyTransferLimit())
                .customerId(account.getCustomerId())
                .createdAt(account.getCreatedAt())
                .active(account.isActive())
                .build();
    }
    
    @Override
    public Account toEntity(AccountDTO dto) {
        
        if (dto == null) return null;
        
        return Account.builder()
                .id(dto.getId())
                .accountNumber(dto.getAccountNumber())
                .type(AccountType.valueOf(dto.getType())) 
                .balance(dto.getBalance())
                .dailyTransferLimit(dto.getDailyTransferLimit())
                .customerId(dto.getCustomerId())
                .createdAt(dto.getCreatedAt())
                .active(dto.isActive())
                .build();
    }
    
    public AccountSummaryDTO toSummaryDTO(Account account) {
        
        if (account == null) return null;
        
        return AccountSummaryDTO.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .type(account.getType().getDisplayName()) 
                .balance(account.getBalance())
                .maskedAccountNumber(maskAccountNumber(account
                        .getAccountNumber()))
                .build();
    }
    
    private String maskAccountNumber(String accountNumber) {
        
        if (accountNumber == null || accountNumber.length() < 4) {
            return "****";
        }
        String lastFour = accountNumber.substring(accountNumber.length() - 4);
        
        return "****" + lastFour;
    }
}
