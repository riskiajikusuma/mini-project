package com.ajik.cardlesswithdrawal.service;

import com.ajik.cardlesswithdrawal.dto.BankAccountPayload;
import com.ajik.cardlesswithdrawal.dto.BankAccountUpdatePayload;
import com.ajik.cardlesswithdrawal.model.BankAccount;

public interface BankAccountService {

    void createBankAccount(BankAccountPayload dto);
    void deleteBankAccount(String accountNumber);
    BankAccount findBankAccount(String accountNumber);
    void updateBalance(String accountNumber, Double accountBalance);
}
