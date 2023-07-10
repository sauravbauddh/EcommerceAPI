package com.example.ecommerce.services;

import com.example.ecommerce.entites.Product;
import com.example.ecommerce.exceptions.EProductException;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(String name, String description, double price) throws EProductException {
        Integer productId = productRepository.create(name, description, price);
        System.out.println(productId);
        return productRepository.findById(productId);
    }

    @Override
    public void removeProduct(int productId) throws EProductException {
        productRepository.removeProduct(productId);
    }

    @Override
    public List<Product> showAllProducts() throws EProductException {
        return productRepository.showAll();
    }
}
