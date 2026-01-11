package com.example.ecommerce.DTO.FakeApiDtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FakeProductDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private RatingDto rating;
}
