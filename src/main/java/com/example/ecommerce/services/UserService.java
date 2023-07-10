package com.example.ecommerce.services;

import com.example.ecommerce.entites.User;
import com.example.ecommerce.exceptions.EAuthException;

public interface UserService {
    User validateUser(String email, String password) throws EAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EAuthException;

}
