package com.example.ecommerce.DTO;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(0)
    private Integer quantity;

    @NotBlank(message = "Category is required")
    @Size(max = 50)
    private String category;

    @NotNull
    private Boolean active;
}
