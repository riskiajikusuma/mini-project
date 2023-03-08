package com.ajik.cardlesswithdrawal.service.impl;

import com.ajik.cardlesswithdrawal.dto.*;
import com.ajik.cardlesswithdrawal.exception.BadRequestException;
import com.ajik.cardlesswithdrawal.model.BankAccount;
import com.ajik.cardlesswithdrawal.model.CardlessWithdrawal;
import com.ajik.cardlesswithdrawal.model.Merchant;
import com.ajik.cardlesswithdrawal.model.MerchantFee;
import com.ajik.cardlesswithdrawal.repository.CardlessWithdrawalRepository;
import com.ajik.cardlesswithdrawal.service.BankAccountService;
import com.ajik.cardlesswithdrawal.service.CardlessWithdrawalService;
import com.ajik.cardlesswithdrawal.service.MerchantFeeService;
import com.ajik.cardlesswithdrawal.service.MerchantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class CardlessWithdrawalServiceImpl implements CardlessWithdrawalService {

    private final CardlessWithdrawalRepository cardlessWithdrawalRepository;
    private final BankAccountService bankAccountService;
    private final MerchantService merchantService;
    private final MerchantFeeService merchantFeeService;

    @Override
    public GenerateReferenceCodeResponse generateReferenceCode(GenerateReferenceCodePayload dto) {
        BankAccount bankAccount = bankAccountService.findBankAccount(dto.getAccountNumber());
        Merchant merchant = merchantService.findMerchant(dto.getMerchantCode());
        MerchantFee merchantFee = merchantFeeService.findMerchantFee(dto.getMerchantCode());

        CardlessWithdrawal cardlessWithdrawal = new CardlessWithdrawal();
        cardlessWithdrawal.setReferenceCode(RandomStringUtils.random(10, false, true));
        cardlessWithdrawal.setMerchant(merchant);
        cardlessWithdrawal.setBankAccount(bankAccount);
        cardlessWithdrawal.setRequestDate(new Date());
        cardlessWithdrawal.setRequestAmount(dto.getRequestAmount());

        cardlessWithdrawalRepository.save(cardlessWithdrawal);

        GenerateReferenceCodeResponse responseDto = new GenerateReferenceCodeResponse();
        responseDto.setReferenceCode(cardlessWithdrawal.getReferenceCode());
        responseDto.setFee(merchantFee.getFee());
        responseDto.setRequestAmount(cardlessWithdrawal.getRequestAmount());

        return responseDto;
    }

    @Override
    public WithdrawalResponse withdrawal(WithdrawalPayload dto) {
        CardlessWithdrawal cardlessWithdrawal = cardlessWithdrawalRepository.findByReferenceCode(dto.getReferenceCode())
                .orElseThrow(() -> new BadRequestException("Reference code not found"));
        BankAccount bankAccount = bankAccountService.findBankAccount(cardlessWithdrawal.getBankAccount().getAccountNumber());
        MerchantFee merchantFee = merchantFeeService.findMerchantFee(dto.getMerchantCode());
        Merchant merchant = merchantService.findMerchant(cardlessWithdrawal.getMerchant().getMerchantCode());

        if (bankAccount.getAccountBalance() > cardlessWithdrawal.getRequestAmount()) {
            bankAccountService.updateBalance(bankAccount.getAccountNumber(),
                    bankAccount.getAccountBalance() - cardlessWithdrawal.getRequestAmount() + merchantFee.getFee());
        } else {
            throw new BadRequestException("insufficient balance");
        }

        merchantService.updateBalance(merchant.getMerchantCode(),
                merchant.getMerchantBalance() + cardlessWithdrawal.getRequestAmount() + merchantFee.getFee());

        WithdrawalResponse responseDto = new WithdrawalResponse();
        responseDto.setTransactionId(cardlessWithdrawal.getId());
        responseDto.setReferenceCode(cardlessWithdrawal.getReferenceCode());
        return responseDto;
    }
}
