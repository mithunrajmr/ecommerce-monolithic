package com.ecommerce.monolith.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.monolith.dto.ProductDto;
import com.ecommerce.monolith.entity.Product;
import com.ecommerce.monolith.exception.ResourceNotFoundException;
import com.ecommerce.monolith.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        return mapToDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        productRepository.delete(product);
    }

    private ProductDto mapToDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    private Product mapToEntity(ProductDto productDto) {
        return new Product(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getQuantity()
        );
    }
}