package com.example.ecommerce.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Cart {
    private int cartId;
    private int userId;
}
