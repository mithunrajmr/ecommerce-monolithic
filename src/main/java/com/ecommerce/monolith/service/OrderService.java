package com.ecommerce.monolith.service;

import java.util.List;

import com.ecommerce.monolith.dto.OrderDto;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderById(Long orderId);

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersByCustomerId(Long customerId);

    OrderDto updateOrder(Long orderId, OrderDto orderDto);

    void deleteOrder(Long orderId);
}