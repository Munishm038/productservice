package com.scaler.productservice.services.impl;

import com.scaler.productservice.dtos.CategoryDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {

    @Autowired
    private RestTemplate restTemplate;

    private Category convertFakeStoreCategoryToCategory(String category) {
        Category newCategory = new Category();
        newCategory.setName(category);
        return newCategory;
    }

    private List<CategoryDto> convertFakeStoreCategoriesToCategoryDto(String[] categories) {
        return Arrays.stream(categories).map(s -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(s);
            return categoryDto;
        }).toList();
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public CategoryDto getSingleCategory(Long id) {
        return null;
    }

    @Override
    public List<CategoryDto> getCategories() {
        String[] categoriesResponse =  restTemplate.getForObject("https://fakestoreapi.com/products/categories",String[].class);

        return convertFakeStoreCategoriesToCategoryDto(categoriesResponse);
    }
}
