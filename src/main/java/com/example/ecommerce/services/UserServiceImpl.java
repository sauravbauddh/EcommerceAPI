package com.example.ecommerce.services;

import com.example.ecommerce.entites.User;
import com.example.ecommerce.exceptions.EAuthException;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EAuthException {
        if(email!=null) email.toLowerCase();
        return userRepository.findByEmailPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email!=null) email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EAuthException("Email Invalid");
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new EAuthException("Email already in use");
        Integer userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    }
}
