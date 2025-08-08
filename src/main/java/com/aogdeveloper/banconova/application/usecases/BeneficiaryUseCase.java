package com.aogdeveloper.banconova.application.usecases;

import com.aogdeveloper.banconova.domain.entities.Beneficiary;
import com.aogdeveloper.banconova.domain.repositories.BeneficiaryRepository;
import com.aogdeveloper.banconova.domain.repositories.CustomerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeneficiaryUseCase {
    private final BeneficiaryRepository beneficiaryRepository;
    private final CustomerRepository customerRepository;
    
    private static final int MAX_BENEFICIARIES_PER_CUSTOMER = 20;
    
    public Beneficiary addBeneficiary(Beneficiary beneficiary) {

        if (!customerRepository.findById(beneficiary.getCustomerId())
                .isPresent()) {
            throw new IllegalArgumentException("Customer not found");
        }
        if (beneficiaryRepository.existsByCustomerIdAndAccountNumber(
                beneficiary.getCustomerId(), beneficiary.getAccountNumber())) {
            throw new IllegalArgumentException("Beneficiary already exists");
        }
        long beneficiaryCount = beneficiaryRepository
                .countByCustomerId(beneficiary.getCustomerId());
        
        if (beneficiaryCount >= MAX_BENEFICIARIES_PER_CUSTOMER) {
            throw new IllegalArgumentException(
                    "Maximum number of beneficiaries reached");
        }
        beneficiary.setCreatedAt(LocalDateTime.now());
        beneficiary.setVerified(false);
        
        return beneficiaryRepository.save(beneficiary);
    }
    
    public List<Beneficiary> getCustomerBeneficiaries(Long customerId) {
        return beneficiaryRepository.findByCustomerIdAndVerified(customerId, 
                true);
    }
    
    public Beneficiary verifyBeneficiary(Long beneficiaryId) {
        Optional<Beneficiary> beneficiaryOpt = beneficiaryRepository
                .findById(beneficiaryId);
        
        if (beneficiaryOpt.isEmpty()) {
            throw new IllegalArgumentException("Beneficiary not found");
        }
        Beneficiary beneficiary = beneficiaryOpt.get();
        beneficiary.setVerified(true);
        
        return beneficiaryRepository.save(beneficiary);
    }
    
    public void deleteBeneficiary(Long beneficiaryId, Long customerId) {
        Optional<Beneficiary> beneficiaryOpt = beneficiaryRepository
                .findById(beneficiaryId);
        
        if (beneficiaryOpt.isEmpty()) {
            throw new IllegalArgumentException("Beneficiary not found");
        }
        Beneficiary beneficiary = beneficiaryOpt.get();
        
        if (!beneficiary.getCustomerId().equals(customerId)) {
            throw new IllegalArgumentException(
                    "Unauthorized to delete this beneficiary");
        }
        beneficiaryRepository.deleteById(beneficiaryId);
    }
}