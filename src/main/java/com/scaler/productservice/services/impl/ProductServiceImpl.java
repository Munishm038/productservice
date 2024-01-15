package com.scaler.productservice.services.impl;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.ResourceAlreadyExistException;
import com.scaler.productservice.exceptions.ResourceNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepo;
import com.scaler.productservice.repositories.ProductRepo;
import com.scaler.productservice.services.SelfProductService;
import com.scaler.productservice.utils.SlugUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class ProductServiceImpl implements SelfProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    ProductDto convertProductToProductDto(Product product) {
        return this.modelMapper.map(product, ProductDto.class);
    }

    Product convertProductDtoToProduct(ProductDto productDto) {
        return this.modelMapper.map(productDto, Product.class);
    }

    Product findProductById(Long id) {
        return this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product", "id", "" + id));
    }

    String getUniqueSlug(String slug) {
        String createdSlug = SlugUtil.createSlug(slug);
        boolean isUnique = this.productRepo.countBySlug(createdSlug) == 0;
        if (isUnique) return createdSlug;
        throw new ResourceAlreadyExistException("Product", "Slug", createdSlug);
    }

    Category findCategoryByName(String name) {

        return this.categoryRepo.findByNameIsIgnoreCase(name).orElseThrow(()->new ResourceNotFoundException("Category", "name", name));
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Category category = this.findCategoryByName(productDto.getCategory().getName());

        String slug = getUniqueSlug(productDto.getSlug());

        Product newProduct = this.convertProductDtoToProduct(productDto);

        Date date = new Date();
        newProduct.setCreatedAt(date);
        newProduct.setUpdatedAt(date);
        newProduct.setDeleted(false);

        newProduct.setCategory(category);
        newProduct.setSlug(slug);

        return this.convertProductToProductDto(this.productRepo.save(newProduct));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = this.findProductById(id);
        Category category;
        String slug = product.getSlug();
        String parsedSlug = SlugUtil.createSlug(productDto.getSlug());

        if(!product.getSlug().equals(parsedSlug)) {
            slug = getUniqueSlug(slug);
        }

        if(!product.getCategory().getName().equals(productDto.getCategory().getName())) {
            category = findCategoryByName(productDto.getCategory().getName());
            product.setCategory(category);
        }

        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setSlug(slug);
        product.setUpdatedAt(new Date());

        return this.convertProductToProductDto(this.productRepo.save(product));
    }

    @Override
    public ProductDto getSingleProduct(Long id) {
        return this.convertProductToProductDto(this.findProductById(id));
    }

    @Override
    public List<ProductDto> getProducts() {
        return this.productRepo.findAll().stream().map(this::convertProductToProductDto).toList();
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.findProductById(id);
        product.setDeleted(true);
        this.productRepo.save(product);
    }
}
