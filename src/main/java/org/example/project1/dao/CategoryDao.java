package org.example.project1.dao;

import java.util.List;
import java.util.Map;

import org.example.project1.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public CategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Category findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Category WHERE category_id = ?",
                (rs, rowNum) -> new Category(rs.getInt("category_id"), rs.getString("name")),
                id);
    }

    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * FROM Category",
                (rs, rowNum) -> new Category(rs.getInt("category_id"), rs.getString("name"))
        );

    }



    public int create(Category category) {
        return jdbcTemplate.update("INSERT INTO Category (name) VALUES (?)", category.getName());
    }


    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Category WHERE category_id = ?", id);
    }
}
