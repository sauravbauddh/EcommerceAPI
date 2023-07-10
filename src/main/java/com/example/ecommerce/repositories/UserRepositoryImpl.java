package com.example.ecommerce.repositories;

import com.example.ecommerce.entites.User;
import com.example.ecommerce.exceptions.EAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD)" +
            " VALUES(NEXTVAL('USERS_SEQ'), ?,?,?,?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM USERS WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM USERS WHERE EMAIL = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        } catch (Exception e) {
            throw new EAuthException("Invalid details, failed to create account");
        }
    }

    @Override
    public User findByEmailPassword(String email, String password) throws EAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, email);
            System.out.println(user.getFirstName());
            if(!password.equals(user.getPassword()))
                throw new EAuthException("Invalid email/password");
            return user;
        } catch (Exception e) {
            throw new EAuthException("Invalid email/password");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class, email);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, userId);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> new User(rs.getInt("USER_ID"),
            rs.getString("FIRST_NAME"),
            rs.getString("LAST_NAME"),
            rs.getString("EMAIL"),
            rs.getString("PASSWORD")
            ));
}
