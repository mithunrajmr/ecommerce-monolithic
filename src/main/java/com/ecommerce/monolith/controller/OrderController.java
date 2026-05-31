package com.ecommerce.monolith.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.monolith.dto.OrderDto;
import com.ecommerce.monolith.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        OrderDto order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<OrderDto> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderDto orderDto) {

        OrderDto updatedOrder = orderService.updateOrder(orderId, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
