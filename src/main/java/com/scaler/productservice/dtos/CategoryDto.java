package com.scaler.productservice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;

    @NotNull(message = "Category name can't be null")
    @Size(message = "Category name should have at least 3 characters")
    private String name;

    private boolean isDeleted;
}
