package com.example.ecommerce.repositories;

import com.example.ecommerce.entites.Product;
import com.example.ecommerce.exceptions.EProductException;

import java.util.List;

public interface ProductRepository {

    Integer create(String productName, String description, double price) throws EProductException;

    Product findById(Integer productId) throws EProductException;

    void removeProduct(Integer productId) throws EProductException;

    List<Product> showAll();
}
