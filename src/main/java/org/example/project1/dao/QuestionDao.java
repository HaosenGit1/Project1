package org.example.project1.dao;

import java.util.List;
import java.util.Map;

import org.example.project1.model.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {

    private final JdbcTemplate jdbcTemplate;

    public QuestionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> findByCategory(int categoryId) {
        return jdbcTemplate.query("SELECT * FROM Question WHERE category_id = ?",
                (rs, rowNum) -> new Question(rs.getInt("question_id"), rs.getInt("category_id"),
                        rs.getString("description"), rs.getBoolean("is_active")),
                categoryId
        );
    }

    public int create(Question question) {
        return jdbcTemplate.update("INSERT INTO Question (category_id, description, is_active) VALUES (?, ?, ?)",
                question.getCategoryId(), question.getDescription(), question.isActive());
    }




    public Question findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Question WHERE question_id = ?",
                (rs, rowNum) -> new Question(
                        rs.getInt("question_id"),
                        rs.getInt("category_id"),
                        rs.getString("description"),
                        rs.getBoolean("is_active")
                ),
                id
        );
    }






    // Fetch all questions
    public List<Question> findAll() {
        return jdbcTemplate.query("SELECT * FROM Question",
                (rs, rowNum) -> new Question(
                        rs.getInt("question_id"),
                        rs.getInt("category_id"),
                        rs.getString("description"),
                        rs.getBoolean("is_active")
                ));
    }







    // Update question
    public int update(Question question) {
        return jdbcTemplate.update(
                "UPDATE Question SET category_id = ?, description = ?, is_active = ? WHERE question_id = ?",
                question.getCategoryId(),
                question.getDescription(),
                question.isActive(),
                question.getQuestionId()
        );
    }

    // Activate question
    public int activate(int id) {
        return jdbcTemplate.update(
                "UPDATE Question SET is_active = TRUE WHERE question_id = ?",
                id
        );
    }

    // Suspend question
    public int suspend(int id) {
        return jdbcTemplate.update(
                "UPDATE Question SET is_active = FALSE WHERE question_id = ?",
                id
        );
    }

    // Delete question (be careful with foreign keys!!!!)
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Question WHERE question_id = ?", id);
    }



}

