package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.models.Product;

import java.util.*;
public interface ProductService {
    Product createProduct(FakeStoreProductDto fakeStoreProductDto);
    Product updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto);
    Product getSingleProduct(Long id);
    List<Product> getProducts();
    void deleteProduct(Long id);
}
