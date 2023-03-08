package com.ajik.cardlesswithdrawal.service.impl;

import com.ajik.cardlesswithdrawal.dto.BankAccountPayload;
import com.ajik.cardlesswithdrawal.exception.BadRequestException;
import com.ajik.cardlesswithdrawal.model.BankAccount;
import com.ajik.cardlesswithdrawal.model.Customer;
import com.ajik.cardlesswithdrawal.repository.BankAccountRepository;
import com.ajik.cardlesswithdrawal.service.BankAccountService;
import com.ajik.cardlesswithdrawal.service.CustomerService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerService customerService;

    @Override
    public void createBankAccount(BankAccountPayload dto) {
        Customer customer = customerService.findCustomer(dto.getIdentityNumber());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(RandomStringUtils.random(16, false, true));
        bankAccount.setCustomer(customer);
        bankAccount.setAccountBalance(dto.getAccountBalance());

        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteBankAccount(String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BadRequestException("Bank account not found"));
        bankAccountRepository.delete(bankAccount);
    }

    @Override
    public BankAccount findBankAccount(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BadRequestException("Bank account not found"));
    }

    @Override
    public void updateBalance(String accountNumber, Double accountBalance) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BadRequestException("Bank account not found"));
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setAccountBalance(accountBalance);

        bankAccountRepository.save(bankAccount);
    }
}