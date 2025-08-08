package com.aogdeveloper.banconova.application.usecases;

import com.aogdeveloper.banconova.domain.entities.Customer;
import com.aogdeveloper.banconova.domain.entities.CustomerAuth;
import com.aogdeveloper.banconova.domain.repositories.AuthRepository;
import com.aogdeveloper.banconova.domain.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUseCase {
    
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final int LOCK_TIME_MINUTES = 15;
    
    public CustomerAuth registerCustomer(String email, 
            String password, 
            Customer customer) {
        
        if (authRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        Customer savedCustomer = customerRepository.save(customer);
        
        CustomerAuth customerAuth = CustomerAuth.builder()
                .customerId(savedCustomer.getId())
                .email(email)
                .passwordHash(passwordEncoder.encode(password))
                .enabled(true)
                .failedAttempts(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
                
        return authRepository.save(customerAuth);
    }
    
    public Optional<CustomerAuth> authenticate(String email, String password) {
        Optional<CustomerAuth> authOpt = authRepository.findByEmail(email);
        
        if (authOpt.isEmpty()) {
            return Optional.empty();
        }
        CustomerAuth auth = authOpt.get();
        
        if (!auth.canAttemptLogin()) {
            throw new IllegalStateException("Account is locked or disabled");
        }
        if (!passwordEncoder.matches(password, auth.getPasswordHash())) {
            handleFailedLogin(auth);
            return Optional.empty();
        }
        handleSuccessfulLogin(auth);
        return Optional.of(auth);
    }
    
    private void handleFailedLogin(CustomerAuth auth) {
        auth.incrementFailedAttempts();
        
        if (auth.getFailedAttempts() >= MAX_FAILED_ATTEMPTS) {
            auth.lockAccount(LOCK_TIME_MINUTES);
        }
        auth.setUpdatedAt(LocalDateTime.now());
        authRepository.save(auth);
    }
    
    private void handleSuccessfulLogin(CustomerAuth auth) {
        auth.resetFailedAttempts();
        auth.setLastLogin(LocalDateTime.now());
        auth.setUpdatedAt(LocalDateTime.now());
        authRepository.save(auth);
    }
}
