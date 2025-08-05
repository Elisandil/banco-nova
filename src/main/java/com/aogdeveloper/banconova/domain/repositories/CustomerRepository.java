package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    Optional<Customer> findByDocumentNumber(String docNumber);
    Optional<Customer> findByEmail(String email);
    List<Customer> findByActive(boolean active);
    List<Customer> findAll();
    void deleteById(Long id);
    boolean existsByDocumentNumber(String docNumber);
    boolean existsByEmail(String email);
}
