package com.ajik.cardlesswithdrawal.service.impl;

import com.ajik.cardlesswithdrawal.dto.MerchantCreatePayload;
import com.ajik.cardlesswithdrawal.exception.BadRequestException;
import com.ajik.cardlesswithdrawal.model.Merchant;
import com.ajik.cardlesswithdrawal.repository.MerchantRepository;
import com.ajik.cardlesswithdrawal.service.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    @Override
    public void createMerchant(MerchantCreatePayload dto) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(dto.getMerchantName());
        merchant.setMerchantCode(dto.getMerchantCode());
        merchant.setMerchantBalance(dto.getMerchantBalance());

        merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchant(String merchantCode) {
        Merchant merchant = merchantRepository.findByMerchantCode(merchantCode)
                .orElseThrow(() -> new BadRequestException("Merchant code not found"));

        merchantRepository.delete(merchant);
    }

    @Override
    public Merchant findMerchant(String merchantCode) {
        return merchantRepository.findByMerchantCode(merchantCode)
                .orElseThrow(()-> new BadRequestException("Merchant not found"));
    }

    @Override
    public void updateBalance(String merchantCode, Double merchantBalance) {
        Merchant merchant = merchantRepository.findByMerchantCode(merchantCode)
                .orElseThrow(() -> new BadRequestException("Merchant code not found"));
        merchant.setMerchantCode(merchantCode);
        merchant.setMerchantBalance(merchantBalance);

        merchantRepository.save(merchant);
    }
}