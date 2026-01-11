package com.example.ecommerce.DTO;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private int quantity;
}
