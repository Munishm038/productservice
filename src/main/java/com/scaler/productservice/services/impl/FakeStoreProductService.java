package com.scaler.productservice.services.impl;

import java.util.*;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements com.scaler.productservice.services.FakeStoreProductService {
    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription((fakeStoreProductDto.getDescription()));
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());

        return product;
    }

    private List<Product> convertFakeStoreProductListToProductList(FakeStoreProductDto[] fakeStoreProductDto) {

        return Arrays.stream(fakeStoreProductDto).map(this::convertFakeStoreProductToProduct).toList();
    }

    @Override
    public Product createProduct(FakeStoreProductDto fakeStoreProductDto) {
        FakeStoreProductDto fakeStoreProductDtoResult = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);

        if (fakeStoreProductDtoResult != null) {
            return this.convertFakeStoreProductToProduct(fakeStoreProductDtoResult);
        }
        return null;
    }

    @Override
    public Product updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class
        );

        if(response.getBody() == null) {
            return null;
        }

        return this.convertFakeStoreProductToProduct(response.getBody());
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if (fakeStoreProductDto != null) {
            return convertFakeStoreProductToProduct(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        return convertFakeStoreProductListToProductList(fakeStoreProductDtos);
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }
}
