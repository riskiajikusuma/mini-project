package com.ajik.cardlesswithdrawal.service;

import com.ajik.cardlesswithdrawal.dto.MerchantFeeCreatePayload;
import com.ajik.cardlesswithdrawal.model.MerchantFee;

public interface MerchantFeeService {

    void addMerchantFee(MerchantFeeCreatePayload dto);
    void deleteMerchantFee(String merchantCode);
    MerchantFee findMerchantFee(String merchantCode);
}