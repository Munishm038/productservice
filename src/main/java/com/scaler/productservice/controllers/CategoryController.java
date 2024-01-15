package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.CategoryDto;
import com.scaler.productservice.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getSingleCategory(@PathVariable Long id) {
        return categoryService.getSingleCategory(id);
    }

    @PostMapping()
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@Valid @PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {

        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }

}
