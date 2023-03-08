package com.ajik.cardlesswithdrawal.repository;

import com.ajik.cardlesswithdrawal.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdentityNumber(String identityNumber);
}
