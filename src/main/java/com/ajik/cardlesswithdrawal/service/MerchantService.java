package com.ajik.cardlesswithdrawal.service;

import com.ajik.cardlesswithdrawal.dto.MerchantCreatePayload;
import com.ajik.cardlesswithdrawal.model.Merchant;

public interface MerchantService {
    void createMerchant(MerchantCreatePayload dto);
    void deleteMerchant(String merchantCode);
    Merchant findMerchant(String merchantCode);
    void updateBalance(String merchantCode, Double merchantBalance);
}
