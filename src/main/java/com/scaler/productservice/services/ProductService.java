package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

import java.util.*;
public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getProducts();
}
