package com.example.ecommerce.repositories;

import com.example.ecommerce.entites.User;
import com.example.ecommerce.exceptions.EAuthException;

public interface UserRepository {
    Integer create(String firstName, String lastName, String email, String password) throws EAuthException;
    User findByEmailPassword(String email, String password) throws EAuthException;
    Integer getCountByEmail(String email);
    User findById(Integer userId);


}
