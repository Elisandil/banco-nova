package com.aogdeveloper.banconova.domain.repositories.usecases;

import com.aogdeveloper.banconova.domain.entities.Customer;
import com.aogdeveloper.banconova.domain.repositories.CustomerRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerUseCase {
    private final CustomerRepository customerRepository;
    
    public Customer createCustomer(Customer customer) {
        
        if (customerRepository.existsByDocumentNumber(
                customer.getDocumentNumber())) {
            throw new IllegalArgumentException(
                    "Customer with this document already exists");
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException(
                    "Customer with this email already exists");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setActive(true);
        
        return customerRepository.save(customer);
    }
    
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    public Optional<Customer> getCustomerByDocument(String documentNumber) {
        return customerRepository.findByDocumentNumber(documentNumber);
    }
    
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository
                .findById(customer.getId());
        
        if (existingCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        return customerRepository.save(customer);
    }
    
    public void deactivateCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        
        if (customer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        Customer customerToUpdate = customer.get();
        customerToUpdate.setActive(false);
        customerRepository.save(customerToUpdate);
    }
}
