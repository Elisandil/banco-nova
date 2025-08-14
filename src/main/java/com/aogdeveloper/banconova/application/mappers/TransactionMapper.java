package com.aogdeveloper.banconova.application.mappers;

import com.aogdeveloper.banconova.application.dtos.transaction.TransactionDTO;
import com.aogdeveloper.banconova.domain.entities.Transaction;
import com.aogdeveloper.banconova.domain.enums.TransactionStatus;
import com.aogdeveloper.banconova.domain.enums.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements DTOMapper<Transaction, TransactionDTO> {
    
    @Override
    public TransactionDTO toDTO(Transaction transaction) {
        
        if (transaction == null) return null;
        
        return TransactionDTO.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .type(transaction.getType().getDisplayName()) 
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .reference(transaction.getReference())
                .createdAt(transaction.getCreatedAt())
                .status(transaction.getStatus().getDisplayName()) 
                .build();
    }
    
    @Override
    public Transaction toEntity(TransactionDTO dto) {
        
        if (dto == null) return null;
        
        return Transaction.builder()
                .id(dto.getId())
                .accountId(dto.getAccountId())
                .type(TransactionType.valueOf(dto.getType()))
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .reference(dto.getReference())
                .createdAt(dto.getCreatedAt())
                .status(TransactionStatus.valueOf(dto.getStatus())) 
                .build();
    }
}
