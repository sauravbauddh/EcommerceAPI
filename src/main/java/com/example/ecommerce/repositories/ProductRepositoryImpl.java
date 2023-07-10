package com.example.ecommerce.repositories;

import com.example.ecommerce.entites.Product;
import com.example.ecommerce.exceptions.EProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    private static final String SQL_CREATE = "INSERT INTO PRODUCTS(PRODUCT_ID, NAME, DESCRIPTION, PRICE) " +
            "VALUES(NEXTVAL('PRODUCT_SEQ'), ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?";

    private static final String SQL_DEL_BY_ID = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";

    private static final String SQL_FETCH_ALL = "SELECT * FROM PRODUCTS";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String productName, String description, double price) throws EProductException {
        // Perform case-insensitive validation to check if a product with the same name already exists
        String sqlCheckExistingProduct = "SELECT COUNT(*) FROM PRODUCTS WHERE LOWER(NAME) = LOWER(?)";
        int existingProductCount = jdbcTemplate.queryForObject(sqlCheckExistingProduct, Integer.class, productName);

        if (existingProductCount > 0) {
            throw new EProductException("Product with the same name already exists");
        }

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, productName);
                preparedStatement.setString(2, description);
                preparedStatement.setDouble(3, price);
                return preparedStatement;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("PRODUCT_ID");
        } catch (Exception e) {
            throw new EProductException("Failed to create product");
        }
    }


    @Override
    public Product findById(Integer productId) throws EProductException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, productRowMapper, productId);
    }

    @Override
    public void removeProduct(Integer productId) throws EProductException {
        int rowsAffected = jdbcTemplate.update(SQL_DEL_BY_ID, productId);
        if (rowsAffected == 0) {
            throw new EProductException("Product with ID " + productId + " does not exist");
        }
    }

    @Override
    public List<Product> showAll() {
        return jdbcTemplate.query(SQL_FETCH_ALL, productRowMapper);
    }


    private RowMapper<Product> productRowMapper = ((rs, rowNum) -> new Product(
            rs.getInt("PRODUCT_ID"),
            rs.getString("NAME"),
            rs.getString("DESCRIPTION"),
            rs.getDouble("PRICE")
    ));
}
