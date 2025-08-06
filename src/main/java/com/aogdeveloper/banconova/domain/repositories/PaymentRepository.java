package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Payment;
import com.aogdeveloper.banconova.domain.entities.Payment.PaymentStatus;
import com.aogdeveloper.banconova.domain.entities.Payment.PaymentType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findByAccountId(Long accountId);
    List<Payment> findByAccountIdAndStatus(Long accountId, PaymentStatus status);
    List<Payment> findByAccountIdAndType(Long accountId, PaymentType type);
    List<Payment> findByAccountIdAndCreatedAtBetween(
        Long accountId, 
        LocalDateTime startDate, 
        LocalDateTime endDate
    );
    List<Payment> findByAccountIdOrderByCreatedAtDesc(Long accountId);
    List<Payment> findByPayeeCode(String payeeCode);
    List<Payment> findByReference(String reference);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByStatusAndCreatedAtBefore(PaymentStatus status, LocalDateTime dateTime);
    List<Payment> findAll();
    void deleteById(Long id);
    long countByAccountId(Long accountId);
}
