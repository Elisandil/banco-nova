package com.aogdeveloper.banconova.domain.repositories;

import com.aogdeveloper.banconova.domain.entities.CreditCard;
import com.aogdeveloper.banconova.domain.entities.CreditCard.CardStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {
    CreditCard save(CreditCard cc);
    Optional<CreditCard> findById(Long id);
    Optional<CreditCard> findByCreditCardNumber(String ccNumber);
    List<CreditCard> findByCustomerId(Long id);
    List<CreditCard> findByCustomerIdAndStatus(Long id, CardStatus status);
    List<CreditCard> findByStatus(CardStatus status);
    List<CreditCard> findByExpiryDateBefore(LocalDateTime expiryDate);
    List<CreditCard> findAll();
    void deleteById(Long id);
    boolean existsByCardNumber(String ccNumber);
    long countByCustomerId(Long id);
}
