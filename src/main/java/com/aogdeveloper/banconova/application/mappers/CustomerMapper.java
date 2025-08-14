package com.aogdeveloper.banconova.application.mappers;

import com.aogdeveloper.banconova.application.dtos.customer.CustomerDTO;
import com.aogdeveloper.banconova.domain.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements DTOMapper<Customer, CustomerDTO> {
    
    @Override
    public CustomerDTO toDTO(Customer customer) {
        
        if (customer == null) return null;
        
        return CustomerDTO.builder()
                .id(customer.getId())
                .documentNumber(customer.getDocumentNumber())
                .documentType(customer.getDocumentType())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .fullName(customer.getFullName()) 
                .email(customer.getEmail())
                .phone(customer.getPhoneNumber())
                .createdAt(customer.getCreatedAt())
                .active(customer.isActive())
                .build();
    }
    
    @Override
    public Customer toEntity(CustomerDTO dto) {
        
        if (dto == null) return null;
        
        return Customer.builder()
                .id(dto.getId())
                .documentNumber(dto.getDocumentNumber())
                .documentType(dto.getDocumentType())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhone())
                .createdAt(dto.getCreatedAt())
                .active(dto.isActive())
                .build();
    }
}