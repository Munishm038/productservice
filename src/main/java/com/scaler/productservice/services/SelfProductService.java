package com.scaler.productservice.services;

import com.scaler.productservice.dtos.ProductDto;

import java.util.List;

public interface SelfProductService {
    ProductDto createProduct(ProductDto product);
    ProductDto updateProduct(Long id, ProductDto product);
    ProductDto getSingleProduct(Long id);
    List<ProductDto> getProducts();
    void deleteProduct(Long id);
}
