package com.scaler.productservice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;

    @NotEmpty(message = "Product title can't be null")
    @Size(min = 5, message = "Product title should have at least 5 characters")
    private String title;

    @Min(value = 0, message = "Value must be greater than or equal to 0")
    private double price;

    @NotNull(message = "Category should be valid")
    private CategoryDto category;

    @NotEmpty(message = "Product description can't be null")
    @Size(min = 15, message = "Product description should have at least 15 characters")
    private String description;

    @NotEmpty(message = "Product image can't be null")
    private String imageUrl;

    @NotEmpty(message = "Product slug can't be null")
    @Size(min = 1, message = "Product slug should have at least 1 character")
    private String slug;

    private boolean isDeleted;
}
