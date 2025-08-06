package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.CustomerAuth;
import java.util.Optional;

public interface AuthRepository {
    Optional<CustomerAuth> findByEmail(String email);
    Optional<CustomerAuth> findByCustomerId(Long customerId);
    CustomerAuth save(CustomerAuth customerAuth);
    void deleteByCustomerId(Long customerId);
    boolean existsByEmail(String email);
}
