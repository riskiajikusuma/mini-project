package com.ajik.cardlesswithdrawal.service.impl;

import com.ajik.cardlesswithdrawal.dto.CustomerCreatePayload;
import com.ajik.cardlesswithdrawal.dto.CustomerResponse;
import com.ajik.cardlesswithdrawal.dto.CustomerUpdatePayload;
import com.ajik.cardlesswithdrawal.exception.BadRequestException;
import com.ajik.cardlesswithdrawal.model.Customer;
import com.ajik.cardlesswithdrawal.repository.CustomerRepository;
import com.ajik.cardlesswithdrawal.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerCreatePayload dto) {
        Customer customer = new Customer();
        customer.setIdentityNumber(dto.getIdentityNumber());
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setDeleted(Boolean.FALSE);

        customerRepository.save(customer);
    }

    public List<CustomerResponse> findCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> {
            CustomerResponse dto = new CustomerResponse();
            dto.setName(customer.getName());
            dto.setIdentityNumber(customer.getIdentityNumber());
            dto.setAddress(customer.getAddress());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerResponse findCustomerByIdentityNumber(String identityNumber) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new BadRequestException("Customer not found"));
        CustomerResponse dto = new CustomerResponse();
        dto.setIdentityNumber(customer.getIdentityNumber());
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    @Override
    public void updateCustomer(String identityNumber, CustomerUpdatePayload dto) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new BadRequestException("Customer not found"));
        customer.setName(dto.getName() == null || dto.getName().isBlank() ? customer.getName() : dto.getName());
        customer.setIdentityNumber(dto.getIdentityNumber() == null || dto.getName().isBlank() ?
                customer.getIdentityNumber() : dto.getIdentityNumber());
        customer.setAddress(dto.getAddress());

        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String identityNumber) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new BadRequestException("Customer not Found!"));
        customer.setDeleted(Boolean.TRUE);

        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomer(String identityNumber) {
        return customerRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(()-> new BadRequestException("Customer not found"));
    }
}