package com.example.ecommerce.repositories;

import com.example.ecommerce.exceptions.ECartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CartRepositoryImpl implements CartRepository {

    private static final String SQL_INSERT = "INSERT INTO CART (ID, USER_ID) VALUES (NEXTVAL('cart_seq'), ?)";
    private static final String SQL_FIND_BY_USER_ID = "SELECT COUNT(*) FROM CART WHERE USER_ID = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void cartInit(int userId) throws ECartException {
        int cartCount = jdbcTemplate.queryForObject(SQL_FIND_BY_USER_ID, Integer.class, userId);
        if (cartCount > 0) {
            throw new ECartException("A cart already exists for user: " + userId);
        }
        jdbcTemplate.update(SQL_INSERT, userId);
    }
}