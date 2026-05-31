package com.ecommerce.monolith.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.monolith.dto.CustomerDto;
import com.ecommerce.monolith.entity.Customer;
import com.ecommerce.monolith.exception.ResourceNotFoundException;
import com.ecommerce.monolith.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return mapToDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        return mapToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        customerRepository.delete(customer);
    }

    private CustomerDto mapToDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }

    private Customer mapToEntity(CustomerDto customerDto) {
        return new Customer(
                customerDto.getCustomerId(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getPhone(),
                customerDto.getAddress()
        );
    }
}