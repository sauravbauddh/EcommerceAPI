package com.example.ecommerce.controllers;

import com.example.ecommerce.entites.Product;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody Map<String, Object> productMap) {
        String productName = (String) productMap.get("name");
        String description = (String) productMap.get("description");
        Double price = (Double) productMap.get("price");

        Product product = productService.addProduct(productName, description, price);

        Map<String, String> map = new HashMap<>();
        map.put("message", "product added");
        return "Added successfully";
    }

    @DeleteMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Integer id) {
        productService.removeProduct(id);
        return "Removed";
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts()  {
        List<Product> products = productService.showAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
