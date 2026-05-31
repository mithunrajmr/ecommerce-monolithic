package com.ecommerce.monolith.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.monolith.dto.OrderDto;
import com.ecommerce.monolith.entity.Customer;
import com.ecommerce.monolith.entity.CustomerOrder;
import com.ecommerce.monolith.entity.Product;
import com.ecommerce.monolith.exception.ResourceNotFoundException;
import com.ecommerce.monolith.repository.CustomerRepository;
import com.ecommerce.monolith.repository.OrderRepository;
import com.ecommerce.monolith.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderDto.getCustomerId()));

        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderDto.getProductId()));

        if (product.getQuantity() < orderDto.getQuantity()) {
            throw new RuntimeException("Insufficient product quantity");
        }

        Double totalAmount = product.getPrice() * orderDto.getQuantity();

        product.setQuantity(product.getQuantity() - orderDto.getQuantity());
        productRepository.save(product);

        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(customer.getCustomerId());
        order.setProductId(product.getProductId());
        order.setQuantity(orderDto.getQuantity());
        order.setTotalAmount(totalAmount);
        order.setOrderStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());

        CustomerOrder savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        return mapToDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderDto.getCustomerId()));

        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderDto.getProductId()));

        Double totalAmount = product.getPrice() * orderDto.getQuantity();

        order.setCustomerId(orderDto.getCustomerId());
        order.setProductId(orderDto.getProductId());
        order.setQuantity(orderDto.getQuantity());
        order.setTotalAmount(totalAmount);
        order.setOrderStatus("UPDATED");

        CustomerOrder updatedOrder = orderRepository.save(order);
        return mapToDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        orderRepository.delete(order);
    }

    private OrderDto mapToDto(CustomerOrder order) {
        return new OrderDto(
                order.getOrderId(),
                order.getCustomerId(),
                order.getProductId(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getOrderDate()
        );
    }
}