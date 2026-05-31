package com.ecommerce.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.monolith.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}