package com.example.ecommerce.DTO;

import com.example.ecommerce.DTO.OrderItemDto;
import com.example.ecommerce.Validation.ValidOrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private Long userId;
    private List<OrderItemDto> items;
    @ValidOrderStatus(message = "not valid status")
    private String status;
}
