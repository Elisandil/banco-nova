package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.Transfer;
import com.aogdeveloper.banconova.domain.entities.Transfer.TransferStatus;
import com.aogdeveloper.banconova.domain.entities.Transfer.TransferType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransferRepository {
    Transfer save(Transfer transfer);
    Optional<Transfer> findByID(Long id);
    List<Transfer> findBySourceAccountID(Long id);
    List<Transfer> findByDestinationAccountID(Long id);
    List<Transfer> findBySourceAccountIDAndStatus(Long id, TransferStatus status);
    List<Transfer> findBySourceAccountIDAndType(Long id, TransferType type);
    List<Transfer> findBySourceAccountIDAndCreatedAtBetween(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
    List<Transfer> findBySourceAccountIDOrderByCreatedAtDesc(Long id);
    List<Transfer> findByStatus(TransferStatus status);
    List<Transfer> findByStatusAndCreatedAtBefore(TransferStatus status, 
        LocalDateTime createDate
    );
    List<Transfer> findAll();
    void deleteByID(Long id);
    long countBySourceAccountID(Long id);
}
