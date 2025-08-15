package com.example.springboot_assignment1.services;

import com.example.springboot_assignment1.entity.Customer;
import com.example.springboot_assignment1.entity.Order;
import com.example.springboot_assignment1.repository.CustomerRepository;
import com.example.springboot_assignment1.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;

  public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
    this.orderRepository = orderRepository;
    this.customerRepository = customerRepository;
  }

  // transactional operation: deduct credit and create order
  @Transactional
  public Order createOrderAndDeduct(UUID customerId, Order order, BigDecimal deductAmount, boolean throwAfterSave) {
    Customer customer = customerRepository.findByIdAndNotDeleted(customerId)
        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

    // Deduct credit
    customer.setCreditLimit(customer.getCreditLimit().subtract(deductAmount));
    customerRepository.save(customer);

    // Set customer on order
    order.setCustomer(customer);

    // 👇 Important: set order on each item
    if (order.getItems() != null) {
      order.getItems().forEach(item -> item.setOrder(order));
    }

    // Save order (items will be cascaded)
    Order saved = orderRepository.save(order);

    if (throwAfterSave) {
      throw new RuntimeException("Simulated failure after saving order — should rollback everything");
    }

    return saved;
  }
}