package com.ecommerce.monolith.service;

import java.util.List;

import com.ecommerce.monolith.dto.CustomerDto;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long customerId);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);

    void deleteCustomer(Long customerId);
}