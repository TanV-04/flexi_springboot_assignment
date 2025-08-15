package com.example.springboot_assignment1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_items")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String productName;
  private int quantity;
  private BigDecimal price;
  @ManyToOne
  @JoinColumn(name = "order_id")
  @JsonBackReference
  private Order order;
}