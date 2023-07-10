package com.example.ecommerce.entites;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CartItem {
    private int cartItemId;
    private int cartId;
    private int productId;
}
