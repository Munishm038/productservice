package com.scaler.productservice.services.impl;

import com.scaler.productservice.dtos.CategoryDto;
import com.scaler.productservice.exceptions.ResourceAlreadyExistException;
import com.scaler.productservice.exceptions.ResourceNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.repositories.CategoryRepo;
import com.scaler.productservice.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Autowired
    CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    Category findCategoryById(Long id) {
        return this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id", ""+id));
    }
    Category findCategoryByName(String name) {
        return this.categoryRepo.findCategoryByName(name);
    }

    Category convertDtoToCategory(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    CategoryDto convertCategoryToDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = findCategoryByName(categoryDto.getName());

        if(category != null) {
            throw new ResourceAlreadyExistException("Category", "name", categoryDto.getName());
        }

        Category newCategory = new Category();
        newCategory.setName(categoryDto.getName());
        newCategory.setCreatedAt(new Date());
        newCategory.setUpdatedAt(new Date());
        newCategory.setDeleted(false);

        return this.convertCategoryToDto(this.categoryRepo.save(newCategory));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
         Category category = this.findCategoryById(id);
         category.setDeleted(true);
         this.categoryRepo.save(category);
    }

    @Override
    public CategoryDto getSingleCategory(Long id) {
        return this.convertCategoryToDto(this.findCategoryById(id));
    }

    @Override
    public List<CategoryDto> getCategories() {
        return this.categoryRepo.findAll().stream().map(this::convertCategoryToDto).toList();
    }
}
