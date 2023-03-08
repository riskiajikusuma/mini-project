package com.ajik.cardlesswithdrawal.repository;

import com.ajik.cardlesswithdrawal.model.CardlessWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardlessWithdrawalRepository extends JpaRepository<CardlessWithdrawal, Long> {

    Optional<CardlessWithdrawal> findByReferenceCode(String referenceCode);

}
