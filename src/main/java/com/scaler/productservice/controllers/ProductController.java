package com.scaler.productservice.controllers;
import java.util.*;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.services.SelfProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final SelfProductService productService;

    @Autowired
    public ProductController(SelfProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getSingleProduct(@PathVariable Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {

        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@Valid @PathVariable("id") Long id, @RequestBody ProductDto productDto) {

        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
