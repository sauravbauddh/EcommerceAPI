package com.example.ecommerce.services;

import com.example.ecommerce.exceptions.ECartException;

public interface CartService {
    public void cartInit(int userId) throws ECartException;
}
