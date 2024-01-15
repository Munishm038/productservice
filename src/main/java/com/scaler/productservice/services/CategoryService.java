package com.scaler.productservice.services;

import com.scaler.productservice.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
    CategoryDto getSingleCategory(Long id);
    List<CategoryDto> getCategories();

}
