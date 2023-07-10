package com.example.ecommerce.services;

import com.example.ecommerce.entites.CartItem;
import com.example.ecommerce.entites.Product;
import com.example.ecommerce.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void addItemToCart(Integer userId, int productId) {
        cartItemRepository.addItemToCart(userId, productId);
    }

    @Override
    public void removeItemFromCart(int userId, int productId) {
        cartItemRepository.removeItemFromCart(userId, productId);
    }

    @Override
    public List<Product> getCartItems(int userId) {
        return cartItemRepository.getCartItems(userId);
    }
}
