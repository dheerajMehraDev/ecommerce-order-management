package com.example.ecommerce.DTO;

import com.example.ecommerce.DTO.OrderItemResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {
    private Long orderId;
    private String status;
    private List<OrderItemResponseDto> items;
}
