package com.ajik.cardlesswithdrawal.controller;

import com.ajik.cardlesswithdrawal.dto.MerchantFeeCreatePayload;
import com.ajik.cardlesswithdrawal.service.MerchantFeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class MerchantFeeController {

    private MerchantFeeService merchantFeeService;

    @PostMapping("/merchant-fee")
    public ResponseEntity<Void> addNewFee(@RequestBody @Valid MerchantFeeCreatePayload dto) {
        merchantFeeService.addMerchantFee(dto);
        return ResponseEntity.created(URI.create("/merchant-fee")).build();
    }

    @DeleteMapping("/merchant-fee/{merchantCode}")
    public ResponseEntity<Void> deleteMerchantFee(@PathVariable @Valid String merchantCode) {
        merchantFeeService.deleteMerchantFee(merchantCode);
        return ResponseEntity.ok().build();
    }
}