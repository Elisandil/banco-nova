package com.aogdeveloper.banconova.application.mappers;

import com.aogdeveloper.banconova.application.dtos.beneficiary.BeneficiaryDTO;
import com.aogdeveloper.banconova.domain.entities.Beneficiary;
import com.aogdeveloper.banconova.domain.enums.BeneficiaryType;
import org.springframework.stereotype.Component;

@Component
public class BeneficiaryMapper implements DTOMapper<Beneficiary, BeneficiaryDTO> {
    
    @Override
    public BeneficiaryDTO toDTO(Beneficiary beneficiary) {
        
        if (beneficiary == null) return null;
        
        return BeneficiaryDTO.builder()
                .id(beneficiary.getId())
                .customerId(beneficiary.getCustomerId())
                .accountNumber(beneficiary.getAccountNumber())
                .accountHolderName(beneficiary.getAccountHolderName())
                .bankCode(beneficiary.getBankCode())
                .bankName(beneficiary.getBankName())
                .alias(beneficiary.getAlias())
                .type(beneficiary.getType().getDisplayName())
                .verified(beneficiary.isVerified())
                .createdAt(beneficiary.getCreatedAt())
                .displayName(beneficiary.getDisplayName()) 
                .build();
    }
    
    @Override
    public Beneficiary toEntity(BeneficiaryDTO dto) {
        
        if (dto == null) return null;
        
        return Beneficiary.builder()
                .id(dto.getId())
                .customerId(dto.getCustomerId())
                .accountNumber(dto.getAccountNumber())
                .accountHolderName(dto.getAccountHolderName())
                .bankCode(dto.getBankCode())
                .bankName(dto.getBankName())
                .alias(dto.getAlias())
                .type(BeneficiaryType.valueOf(dto.getType()))
                .verified(dto.isVerified())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}