package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<Product> products;
    private String name;
}
