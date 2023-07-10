package com.example.ecommerce.controllers;

import com.example.ecommerce.entites.CartItem;
import com.example.ecommerce.entites.Product;
import com.example.ecommerce.services.CartItemService;
import com.example.ecommerce.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartItemService cartItemService;
    private final CartService cartService;

    public CartController(CartItemService cartItemService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
    }

    @PostMapping("")
    public ResponseEntity<String> cartInit(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        cartService.cartInit(userId);
        return ResponseEntity.ok("Cart Initialized, Empty right now");
    }


    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(HttpServletRequest request, @RequestBody Map<String, Integer> productId) {
        int userId = (int) request.getAttribute("userId");
        cartItemService.addItemToCart(userId, productId.get("productId"));
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added to cart successfully");
    }

    @DeleteMapping("/removeItem/{productId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable int productId, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        cartItemService.removeItemFromCart(userId, productId);
        return ResponseEntity.status(HttpStatus.OK).body("Item removed from cart successfully");
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getCartItems(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        List<Product> cartItems = cartItemService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }
}
