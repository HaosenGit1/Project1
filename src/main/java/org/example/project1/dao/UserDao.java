package org.example.project1.dao;


import java.util.List;
import java.util.Map;

import org.example.project1.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getInt("user_id"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("firstname"),
            rs.getString("lastname"),
            rs.getBoolean("is_active"),
            rs.getBoolean("is_admin")
    );

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM User", userRowMapper);
    }

    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE user_id = ?", userRowMapper, id);
    }

    public int create(User user) {
        return jdbcTemplate.update(
                "INSERT INTO User (email, password, firstname, lastname, is_active, is_admin) VALUES (?, ?, ?, ?, ?, ?)",
                user.getEmail(), user.getPassword(), user.getFirstname(), user.getLastname(), user.isActive(), user.isAdmin()
        );
    }

    public int update(User user) {
        return jdbcTemplate.update(
                "UPDATE User SET email = ?, password = ?, firstname = ?, lastname = ?, is_active = ?, is_admin = ? WHERE user_id = ?",
                user.getEmail(), user.getPassword(), user.getFirstname(), user.getLastname(), user.isActive(), user.isAdmin(), user.getUserId()
        );
    }

    public int delete(int id) {
//        return jdbcTemplate.update("DELETE FROM User WHERE user_id = ?", id);// can not be deleted as foreign key
        return jdbcTemplate.update("UPDATE User SET is_active = false WHERE user_id = ?", id);


    }

    public User findByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT * FROM User WHERE email = ?", userRowMapper, email);
        return users.isEmpty() ? null : users.get(0);
    }

    public int suspend(int userId) {
        return jdbcTemplate.update("UPDATE User SET is_active = FALSE WHERE user_id = ?", userId);
    }

    public int activate(int userId) {
        return jdbcTemplate.update("UPDATE User SET is_active = TRUE WHERE user_id = ?", userId);
    }



}
