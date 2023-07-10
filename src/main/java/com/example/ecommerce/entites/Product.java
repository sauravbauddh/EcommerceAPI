package com.example.ecommerce.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
}
