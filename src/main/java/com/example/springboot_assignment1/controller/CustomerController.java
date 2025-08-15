package com.example.springboot_assignment1.controller;

import com.example.springboot_assignment1.entity.Customer;
import com.example.springboot_assignment1.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  // Create a new customer
  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    return ResponseEntity.ok(customerService.save(customer));
  }

  // List all customers (excluding soft-deleted ones)
  @GetMapping
  public ResponseEntity<List<Customer>> listCustomers() {
    return ResponseEntity.ok(customerService.findAllNotDeleted());
  }

  // Soft delete a customer by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> softDeleteCustomer(@PathVariable UUID id) {
    customerService.softDelete(id);
    return ResponseEntity.noContent().build();
  }
}
