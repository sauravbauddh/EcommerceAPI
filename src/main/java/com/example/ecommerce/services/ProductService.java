package com.example.ecommerce.services;

import com.example.ecommerce.entites.Product;
import com.example.ecommerce.exceptions.EProductException;

import java.util.List;

public interface ProductService {
    Product addProduct(String name, String description, double price) throws EProductException;
    void removeProduct(int productId) throws EProductException;
    List<Product> showAllProducts() throws EProductException;

}
