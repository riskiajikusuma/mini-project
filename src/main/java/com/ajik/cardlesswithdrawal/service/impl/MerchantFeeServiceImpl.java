package com.ajik.cardlesswithdrawal.service.impl;

import com.ajik.cardlesswithdrawal.dto.MerchantFeeCreatePayload;
import com.ajik.cardlesswithdrawal.exception.BadRequestException;
import com.ajik.cardlesswithdrawal.model.Merchant;
import com.ajik.cardlesswithdrawal.model.MerchantFee;
import com.ajik.cardlesswithdrawal.repository.MerchantFeeRepository;
import com.ajik.cardlesswithdrawal.service.MerchantFeeService;
import com.ajik.cardlesswithdrawal.service.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MerchantFeeServiceImpl implements MerchantFeeService {

    private final MerchantFeeRepository merchantFeeRepository;

    private final MerchantService merchantService;

    @Override
    public void addMerchantFee(MerchantFeeCreatePayload dto) {
        Merchant merchant = merchantService.findMerchant(dto.getMerchantCode());
        MerchantFee merchantFee = new MerchantFee();
        merchantFee.setFee(dto.getFee());
        merchantFee.setMerchant(merchant);

        merchantFeeRepository.save(merchantFee);
    }

    @Override
    public void deleteMerchantFee(String merchantCode) {
        MerchantFee merchantFee = merchantFeeRepository.findMerchantFeeByMerchantCode(merchantCode)
                .orElseThrow(() -> new BadRequestException("Merchant fee not found"));
        merchantFeeRepository.delete(merchantFee);
    }

    @Override
    public MerchantFee findMerchantFee(String merchantCode) {
        return merchantFeeRepository.findMerchantFeeByMerchantCode(merchantCode)
                .orElseThrow(() -> new BadRequestException("Merchant fee not found"));
    }
}