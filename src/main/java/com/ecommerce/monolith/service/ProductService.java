package com.ecommerce.monolith.service;

import java.util.List;

import com.ecommerce.monolith.dto.ProductDto;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProductById(Long productId);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);
}