package com.ajik.cardlesswithdrawal.controller;

import com.ajik.cardlesswithdrawal.dto.BankAccountPayload;
import com.ajik.cardlesswithdrawal.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/bank-account")
    public ResponseEntity<Void> createBankAccount(@RequestBody @Valid BankAccountPayload dto) {
        bankAccountService.createBankAccount(dto);
        return ResponseEntity.created(URI.create("/bank-account")).build();
    }

    @DeleteMapping("/bank-account/{accountNumber}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable String accountNumber) {
        bankAccountService.deleteBankAccount(accountNumber);
        return ResponseEntity.ok().build();
    }
}