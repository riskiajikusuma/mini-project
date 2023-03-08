package com.ajik.cardlesswithdrawal.controller;

import com.ajik.cardlesswithdrawal.dto.MerchantCreatePayload;
import com.ajik.cardlesswithdrawal.service.MerchantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/merchant")
    public ResponseEntity<Void> createNewMerchant(@RequestBody @Valid MerchantCreatePayload dto) {
        merchantService.createMerchant(dto);
        return ResponseEntity.created(URI.create("/merchant")).build();
    }

    @DeleteMapping("/merchant/{merchantCode}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable String merchantCode) {
        merchantService.deleteMerchant(merchantCode);
        return ResponseEntity.ok().build();
    }
}