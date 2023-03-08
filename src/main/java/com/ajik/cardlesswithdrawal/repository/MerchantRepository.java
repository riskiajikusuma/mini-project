package com.ajik.cardlesswithdrawal.repository;

import com.ajik.cardlesswithdrawal.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    Optional<Merchant> findByMerchantCode(String merchantCode);
}