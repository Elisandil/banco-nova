package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Beneficiary;
import com.aogdeveloper.banconova.domain.entities.Beneficiary.BeneficiaryType;
import java.util.List;
import java.util.Optional;

public interface BeneficiaryRepository {
    Beneficiary save(Beneficiary beneficiary);
    Optional<Beneficiary> findById(Long id);
    List<Beneficiary> findByCustomerId(Long customerId);
    List<Beneficiary> findByCustomerIdAndVerified(Long customerId, 
            boolean verified);
    List<Beneficiary> findByCustomerIdAndType(Long customerId, 
            BeneficiaryType type);
    Optional<Beneficiary> findByCustomerIdAndAccountNumber(Long customerId, 
            String accountNumber);
    List<Beneficiary> findByAccountNumber(String accountNumber);
    List<Beneficiary> findByBankCode(String bankCode);
    List<Beneficiary> findAll();
    void deleteById(Long id);
    boolean existsByCustomerIdAndAccountNumber(Long customerId, 
            String accountNumber);
    long countByCustomerId(Long customerId);
}
