package com.ajik.cardlesswithdrawal.controller;

import com.ajik.cardlesswithdrawal.dto.CustomerCreatePayload;
import com.ajik.cardlesswithdrawal.dto.CustomerResponse;
import com.ajik.cardlesswithdrawal.dto.CustomerUpdatePayload;
import com.ajik.cardlesswithdrawal.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<Void> createNewCustomer(@RequestBody @Valid CustomerCreatePayload dto) {
        customerService.createCustomer(dto);
        return ResponseEntity.created(URI.create("/customer")).build();
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerResponse>> findCustomers() {
        return ResponseEntity.ok().body(customerService.findCustomers());
    }

    @GetMapping("/customer/{identityNumber}")
    public ResponseEntity<CustomerResponse> findCustomerByIdentityNumber(@PathVariable String identityNumber) {
        return ResponseEntity.ok().body(customerService.findCustomerByIdentityNumber(identityNumber));
    }

    @PutMapping("/customer/{identityNumber}")
    public ResponseEntity<Void> createNewCustomer(@PathVariable String identityNumber,
                                                  @RequestBody @Valid CustomerUpdatePayload dto) {
        customerService.updateCustomer(identityNumber, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/customer/{identityNumber}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String identityNumber) {
        customerService.deleteCustomer(identityNumber);
        return ResponseEntity.ok().build();
    }
}