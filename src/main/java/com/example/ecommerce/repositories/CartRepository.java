package com.example.ecommerce.repositories;

import com.example.ecommerce.exceptions.ECartException;

public interface CartRepository {
    public void cartInit(int userId) throws ECartException;
}
