package com.ajik.cardlesswithdrawal.service;

import com.ajik.cardlesswithdrawal.dto.CustomerCreatePayload;
import com.ajik.cardlesswithdrawal.dto.CustomerResponse;
import com.ajik.cardlesswithdrawal.dto.CustomerUpdatePayload;
import com.ajik.cardlesswithdrawal.model.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomer(CustomerCreatePayload dto);
    List<CustomerResponse> findCustomers();
    CustomerResponse findCustomerByIdentityNumber(String identityNumber);
    void updateCustomer(String identityNumber, CustomerUpdatePayload dto);
    void deleteCustomer(String identityNumber);
    Customer findCustomer(String identityNumber);
}