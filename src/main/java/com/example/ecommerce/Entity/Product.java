package com.example.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@EntityListeners(AuditingEntityListener.class)

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;   // stock

    private String category;

    private Boolean active;

    //    @CreationTimestamp
    @CreatedDate
    private LocalDateTime createdAt;

    //    @UpdateTimestamp
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();
}
