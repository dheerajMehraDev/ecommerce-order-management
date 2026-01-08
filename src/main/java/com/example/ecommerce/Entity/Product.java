package com.example.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;   // stock

    private String category;

    private Boolean active;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
