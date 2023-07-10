package com.example.ecommerce.services;

import com.example.ecommerce.exceptions.ECartException;
import com.example.ecommerce.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository repository;
    @Override
    public void cartInit(int userId) throws ECartException {
        repository.cartInit(userId);
    }
}
