package org.example.project1.dao;

import java.util.List;
import java.util.Map;


import org.example.project1.model.Quiz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizDao {

    private final JdbcTemplate jdbcTemplate;

    public QuizDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Quiz> findByUser(int userId) {
        return jdbcTemplate.query("SELECT * FROM Quiz WHERE user_id = ?",
                (rs, rowNum) -> new Quiz(rs.getInt("quiz_id"), rs.getInt("user_id"),
                        rs.getInt("category_id"), rs.getString("name"),
                        rs.getTimestamp("time_start"), rs.getTimestamp("time_end")),
                userId
        );
    }




    public int create(Quiz quiz) {
        return jdbcTemplate.update("INSERT INTO Quiz (user_id, category_id, name) VALUES (?, ?, ?)",
                quiz.getUserId(), quiz.getCategoryId(), quiz.getName());
    }
    public int createNewQuizForUser(int userId, int categoryId) {
        jdbcTemplate.update("INSERT INTO Quiz (user_id, category_id, time_start) VALUES (?, ?, NOW())", userId, categoryId);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public void markQuizCompleted(int quizId) {
        jdbcTemplate.update("UPDATE Quiz SET time_end = NOW() WHERE quiz_id = ?", quizId);
    }

    public Quiz findById(int quizId) {
        // TODO: Write SQL to get the quiz by ID
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Quiz WHERE quiz_id = ?",
                (rs, rowNum) -> new Quiz(
                        rs.getInt("quiz_id"),
                        rs.getInt("user_id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getTimestamp("time_start"),
                        rs.getTimestamp("time_end")
                ),
                quizId
        );
    }


    public List<Map<String, Object>> findQuizResults(Integer categoryId, Integer userId, String sortBy, int page, int pageSize) {
        StringBuilder sql = new StringBuilder(
                "SELECT q.quiz_id, q.time_start, c.name AS category_name, u.firstname, u.lastname, " +
                        "COUNT(qq.question_id) AS question_count, " +
                        "SUM(CASE WHEN qq.user_choice_id = correct.choice_id THEN 1 ELSE 0 END) AS score " +
                        "FROM Quiz q " +
                        "JOIN User u ON q.user_id = u.user_id " +
                        "JOIN Category c ON q.category_id = c.category_id " +
                        "JOIN QuizQuestion qq ON q.quiz_id = qq.quiz_id " +
                        "JOIN Choice correct ON correct.question_id = qq.question_id AND correct.is_correct = TRUE " +
                        "WHERE 1=1 "
        );

        if (categoryId != null) sql.append("AND q.category_id = ").append(categoryId).append(" ");
        if (userId != null) sql.append("AND q.user_id = ").append(userId).append(" ");

        sql.append("GROUP BY q.quiz_id, c.name, u.firstname, u.lastname, q.time_start ");

        if ("user".equals(sortBy)) {
            sql.append("ORDER BY u.firstname ASC ");
        } else if ("category".equals(sortBy)) {
            sql.append("ORDER BY c.name ASC ");
        } else {
            sql.append("ORDER BY q.time_start DESC ");
        }

        int offset = (page - 1) * pageSize;
        sql.append("LIMIT ").append(pageSize).append(" OFFSET ").append(offset);

        return jdbcTemplate.queryForList(sql.toString());
    }

    public int countQuizResults(Integer categoryId, Integer userId) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT q.quiz_id) FROM Quiz q WHERE 1=1 ");

        if (categoryId != null) sql.append("AND q.category_id = ").append(categoryId).append(" ");
        if (userId != null) sql.append("AND q.user_id = ").append(userId).append(" ");

        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }



}
