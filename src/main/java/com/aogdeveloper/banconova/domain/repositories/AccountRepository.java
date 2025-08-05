package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Account;
import com.aogdeveloper.banconova.domain.entities.Account.AccountType;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findByAccountNumber(String accNumber);
    Optional<Account> findById(Long id);
    List<Account> findByCustomerID(Long id);
    List<Account> findByActiveAndCustomerID(boolean active, Long id);
    List<Account> findByType(AccountType type);
    List<Account> findAll();
    void deleteByID(Long id);
    boolean existsByAccountNumber(String accNumber);
    long countByCustomerID(Long id);
}
