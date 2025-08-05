package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Transaction;
import com.aogdeveloper.banconova.domain.entities.Transaction.TransactionStatus;
import com.aogdeveloper.banconova.domain.entities.Transaction.TransactionType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction trans);
    Optional<Transaction> findByID(Long id);
    List<Transaction> findByAccID(Long id);
    List<Transaction> findByAccIDAndStatus(Long id, TransactionStatus status);
    List<Transaction> findByAccIDAndType(Long id, TransactionType type);
    List<Transaction> findByAccIDAndCreatedAtBetween(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate    
    );
    List<Transaction> findByAccIDOrderByCreatedAtDesc(Long id);
    List<Transaction> findByAccIDAndCreatedAtBetweenOrderByCreatedAtDesc(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
    List<Transaction> findByReference(String reference);
    List<Transaction> findAll();
    void deleteById(Long id);
    long countByAccID(Long id);
}
