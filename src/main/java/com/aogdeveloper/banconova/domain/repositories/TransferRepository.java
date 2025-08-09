package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Transfer;
import com.aogdeveloper.banconova.domain.entities.Transfer.TransferStatus;
import com.aogdeveloper.banconova.domain.entities.Transfer.TransferType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransferRepository {
    Transfer save(Transfer transfer);
    Optional<Transfer> findById(Long id);
    List<Transfer> findBySourceAccountId(Long id);
    List<Transfer> findByDestinationAccountId(Long id);
    List<Transfer> findBySourceAccountIdAndStatus(Long id, TransferStatus status);
    List<Transfer> findBySourceAccountIdAndType(Long id, TransferType type);
    List<Transfer> findBySourceAccountIdAndCreatedAtBetween(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
    List<Transfer> findBySourceAccountIdOrderByCreatedAtDesc(Long id);
    List<Transfer> findByStatus(TransferStatus status);
    List<Transfer> findByStatusAndCreatedAtBefore(TransferStatus status, 
        LocalDateTime createDate
    );
    List<Transfer> findAll();
    void deleteById(Long id);
    long countBySourceAccountId(Long id);
}
