package com.aogdeveloper.banconova.application.mappers;

import com.aogdeveloper.banconova.application.dtos.payment.PaymentDTO;
import com.aogdeveloper.banconova.domain.entities.Payment;
import com.aogdeveloper.banconova.domain.enums.PaymentType;
import com.aogdeveloper.banconova.domain.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper implements DTOMapper<Payment, PaymentDTO> {
    
    @Override
    public PaymentDTO toDTO(Payment payment) {
        
        if (payment == null) return null;
        
        return PaymentDTO.builder()
                .id(payment.getId())
                .accountId(payment.getAccountId())
                .type(payment.getType().getDisplayName()) 
                .payCode(payment.getPayCode()) 
                .payName(payment.getPayName())
                .reference(payment.getReference())
                .amount(payment.getAmount())
                .status(payment.getStatus().getDisplayName())
                .createdAt(payment.getCreatedAt())
                .processedAt(payment.getProcessedAt())
                .build();
    }
    
    @Override
    public Payment toEntity(PaymentDTO dto) {
        
        if (dto == null) return null;
        
        return Payment.builder()
                .id(dto.getId())
                .accountId(dto.getAccountId())
                .type(PaymentType.valueOf(dto.getType()))
                .payCode(dto.getPayCode())
                .payName(dto.getPayName())
                .reference(dto.getReference())
                .amount(dto.getAmount())
                .status(PaymentStatus.valueOf(dto.getStatus()))
                .createdAt(dto.getCreatedAt())
                .processedAt(dto.getProcessedAt())
                .build();
    }
}