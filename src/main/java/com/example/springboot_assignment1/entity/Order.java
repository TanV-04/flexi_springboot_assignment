package com.example.springboot_assignment1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime orderDate;
  @Enumerated(EnumType.STRING)
  private OrderStatus status;
  private BigDecimal totalAmount;
  @ManyToOne
  @JoinColumn(name = "customer_id")
  @JsonBackReference
  private Customer customer;
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<OrderItem> items = new ArrayList<>();

  @PrePersist
  public void prePersist() {
    if (orderDate == null)
      orderDate = LocalDateTime.now();
    if (status == null)
      status = OrderStatus.PENDING;
    if (totalAmount == null)
      totalAmount = BigDecimal.ZERO;
  }
}