package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Transaction;
import com.aogdeveloper.banconova.domain.entities.Transaction.TransactionStatus;
import com.aogdeveloper.banconova.domain.entities.Transaction.TransactionType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction trans);
    Optional<Transaction> findById(Long id);
    List<Transaction> findByAccId(Long id);
    List<Transaction> findByAccIdAndStatus(Long id, TransactionStatus status);
    List<Transaction> findByAccIdAndType(Long id, TransactionType type);
    List<Transaction> findByAccIdAndCreatedAtBetween(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate    
    );
    List<Transaction> findByAccIdOrderByCreatedAtDesc(Long id);
    List<Transaction> findByAccIdAndCreatedAtBetweenOrderByCreatedAtDesc(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
    List<Transaction> findByReference(String reference);
    List<Transaction> findAll();
    void deleteById(Long id);
    long countByAccId(Long id);
}
