package com.example.ecommerce.services;

import com.example.ecommerce.entites.CartItem;
import com.example.ecommerce.entites.Product;

import java.util.List;

public interface CartItemService {

    void addItemToCart(Integer userId, int productId);

    void removeItemFromCart(int userId, int productId);

    List<Product> getCartItems(int userId);

}
