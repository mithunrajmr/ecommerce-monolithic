package com.ecommerce.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.monolith.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}