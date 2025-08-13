package com.aogdeveloper.banconova.application.mappers;

import com.aogdeveloper.banconova.application.dtos.creditcard.CreditCardDTO;
import com.aogdeveloper.banconova.application.dtos.creditcard.CreditCardSummaryDTO;
import com.aogdeveloper.banconova.domain.entities.CreditCard;
import com.aogdeveloper.banconova.domain.enums.CardStatus;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper implements DTOMapper<CreditCard, CreditCardDTO> {
    
    @Override
    public CreditCardDTO toDTO(CreditCard creditCard) {
        
        if (creditCard == null) return null;
        
        return CreditCardDTO.builder()
                .id(creditCard.getId())
                .maskedNumber(creditCard.getMaskedCardNumber())
                .customerId(creditCard.getCustomerId())
                .creditLimit(creditCard.getCreditLimit())
                .availableCredit(creditCard.getAvailableCredit())
                .currentBalance(creditCard.getCurrentBalance())
                .expiryDate(creditCard.getExpiryDate())
                .status(creditCard.getStatus().getDisplayName()) 
                .createdAt(creditCard.getCreatedAt())
                .build();
    }
    
    @Override
    public CreditCard toEntity(CreditCardDTO dto) {
        
        if (dto == null) return null;
        
        return CreditCard.builder()
                .id(dto.getId())
                .maskedNumber(dto.getMaskedNumber())
                .customerId(dto.getCustomerId())
                .creditLimit(dto.getCreditLimit())
                .availableCredit(dto.getAvailableCredit())
                .currentBalance(dto.getCurrentBalance())
                .expiryDate(dto.getExpiryDate())
                .status(CardStatus.valueOf(dto.getStatus()))
                .createdAt(dto.getCreatedAt())
                .build();
    }
    
    public CreditCardSummaryDTO toSummaryDTO(CreditCard creditCard) {
        
        if (creditCard == null) return null;
        
        return CreditCardSummaryDTO.builder()
                .id(creditCard.getId())
                .maskedNumber(creditCard.getMaskedCardNumber())
                .availableCredit(creditCard.getAvailableCredit())
                .currentBalance(creditCard.getCurrentBalance())
                .status(creditCard.getStatus().getDisplayName()) 
                .build();
    }
}