package com.scaler.productservice.services.impl;

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
    private List<Category> convertFakeStoreCategoriesToCategory(String[] categories) {
        return Arrays.stream(categories).map(this::convertFakeStoreCategoryToCategory).toList();
    }
    @Override
    public List<Category> getCategories() {
        String[] categoriesResponse =  restTemplate.getForObject("https://fakestoreapi.com/products/categories",String[].class);

        return convertFakeStoreCategoriesToCategory(categoriesResponse);
    }
}
