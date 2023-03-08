package com.ajik.cardlesswithdrawal.controller;

import com.ajik.cardlesswithdrawal.dto.*;
import com.ajik.cardlesswithdrawal.service.CardlessWithdrawalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CardlessWithdrawalController {

    private final CardlessWithdrawalService cardlessWithdrawalService;

    @PostMapping("/cardless-withdrawal/generate-reference-code")
    public ResponseEntity<GenerateReferenceCodeResponse> generateReferenceCode(
            @RequestBody @Valid GenerateReferenceCodePayload dto) {
        return ResponseEntity.ok().body(cardlessWithdrawalService.generateReferenceCode(dto));
    }

    @PostMapping("/cardless-withdrawal/withdrawal")
    public ResponseEntity<WithdrawalResponse> withdrawal(@RequestBody @Valid WithdrawalPayload dto) {
        return ResponseEntity.ok().body(cardlessWithdrawalService.withdrawal(dto));
    }
}
