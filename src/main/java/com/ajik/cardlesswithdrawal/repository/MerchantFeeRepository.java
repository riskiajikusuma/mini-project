package com.ajik.cardlesswithdrawal.repository;

import com.ajik.cardlesswithdrawal.model.MerchantFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MerchantFeeRepository extends JpaRepository<MerchantFee, Long> {
    @Query("SELECT mf FROM MerchantFee mf JOIN Merchant m ON m.id = mf.merchant.id WHERE m.merchantCode = :merchantCode")
    Optional<MerchantFee> findMerchantFeeByMerchantCode(String merchantCode);
}
