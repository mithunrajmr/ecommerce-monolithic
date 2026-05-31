package com.ecommerce.monolith.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderDto {

    private Long orderId;

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotNull(message = "Product id is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    private Double totalAmount;
    private String orderStatus;
    private LocalDateTime orderDate;

    public OrderDto() {
    }

    public OrderDto(Long orderId, Long customerId, Long productId, Integer quantity, Double totalAmount,
            String orderStatus, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}