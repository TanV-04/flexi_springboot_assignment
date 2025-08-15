package com.example.springboot_assignment1.services;

import com.example.springboot_assignment1.entity.Customer;
import com.example.springboot_assignment1.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

  public List<Customer> findAllNotDeleted() {
    return customerRepository.findAllNotDeleted(); // You'll need this method in your repository
  }

  public void softDelete(UUID id) {
    customerRepository.softDeleteCustomer(id); // Assumes you have a query for this
  }
}
