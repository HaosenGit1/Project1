package org.example.project1.dao;



import org.example.project1.model.Choice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import java.util.List;

@Repository
public class ChoiceDao {

    private final JdbcTemplate jdbcTemplate;

    public ChoiceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Choice> findByQuestion(int questionId) {
        return jdbcTemplate.query("SELECT * FROM Choice WHERE question_id = ?",
                (rs, rowNum) -> new Choice(rs.getInt("choice_id"), rs.getInt("question_id"),
                        rs.getString("description"), rs.getBoolean("is_correct")),
                questionId
        );
    }

    public int create(Choice choice) {
        return jdbcTemplate.update("INSERT INTO Choice (question_id, description, is_correct) VALUES (?, ?, ?)",
                choice.getQuestionId(), choice.getDescription(), choice.isCorrect());
    }
}
