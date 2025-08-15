package com.example.springboot_assignment1.repository;

import java.util.UUID;

public interface CustomerRepositoryCustom {
  void softDeleteCustomer(UUID id);
}