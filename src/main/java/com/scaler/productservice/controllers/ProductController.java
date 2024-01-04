package com.scaler.productservice.controllers;
import java.util.*;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product createProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto) {

        return productService.createProduct(fakeStoreProductDto);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto) {

        return productService.updateProduct(id, fakeStoreProductDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
