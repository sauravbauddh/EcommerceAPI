package com.example.ecommerce.repositories;

import com.example.ecommerce.entites.CartItem;
import com.example.ecommerce.entites.Product;
import com.example.ecommerce.exceptions.ECartException;

import java.util.List;

public interface CartItemRepository {
    void addItemToCart(int userId, int productId) throws ECartException;
    void removeItemFromCart(int userId, int productId) throws ECartException;
    List<Product> getCartItems(int userId) throws ECartException;
}
