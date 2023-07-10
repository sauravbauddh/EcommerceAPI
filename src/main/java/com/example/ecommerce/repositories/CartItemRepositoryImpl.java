package com.example.ecommerce.repositories;

import com.example.ecommerce.entites.Product;
import com.example.ecommerce.exceptions.ECartException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemRepositoryImpl implements CartItemRepository {

    private static final String SQL_INSERT = "INSERT INTO cart_item (id,cart_id, product_id) VALUES (3, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM cart_item WHERE cart_id = ? AND product_id = ?";
    private static final String SQL_SHOW_ALL_PRODUCTS = "SELECT * FROM products WHERE product_id IN (SELECT product_id FROM cart_item WHERE cart_id = ?)";
    private static final String SQL_GET_CART_ID = "SELECT id FROM cart WHERE user_id = ?";
    private final JdbcTemplate jdbcTemplate;

    public CartItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addItemToCart(int userId, int productId) throws ECartException {
        int cartId = jdbcTemplate.queryForObject(SQL_GET_CART_ID, Integer.class, userId);
        if (cartId > 0){
            jdbcTemplate.update(SQL_INSERT, cartId, productId);
        }
    }

    @Override
    public void removeItemFromCart(int userId, int productId) throws ECartException {
        int cartId = jdbcTemplate.queryForObject(SQL_GET_CART_ID, Integer.class, userId);
        int rowsAffected = jdbcTemplate.update(SQL_DELETE, cartId, productId);
        if (rowsAffected == 0) {
            throw new ECartException("Item not found in the cart");
        }
    }

    @Override
    public List<Product> getCartItems(int userId) throws ECartException {
        int cartId = jdbcTemplate.queryForObject(SQL_GET_CART_ID, Integer.class, userId);
        List<Product> cartItems = jdbcTemplate.query(SQL_SHOW_ALL_PRODUCTS, productRowMapper, cartId);
        if (cartItems.isEmpty()) {
            System.out.println("Cart empty, add some items");
        }
        return cartItems;
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
            rs.getInt("product_id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price")
    );

}
