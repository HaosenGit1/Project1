

package org.example.project1.dao;
import java.util.List;
import java.util.Map;

import org.example.project1.model.QuizQuestion;
import org.example.project1.model.Question;
import org.example.project1.model.Choice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizQuestionDao {

    private final JdbcTemplate jdbcTemplate;
    private final QuestionDao questionDao;
    private final ChoiceDao choiceDao;

    public QuizQuestionDao(JdbcTemplate jdbcTemplate, QuestionDao questionDao, ChoiceDao choiceDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionDao = questionDao;
        this.choiceDao = choiceDao;
    }

    public List<QuizQuestion> findByQuiz(int quizId) {
        List<QuizQuestion> quizQuestions = jdbcTemplate.query(
                "SELECT * FROM QuizQuestion WHERE quiz_id = ?",
                (rs, rowNum) -> new QuizQuestion(
                        rs.getInt("qq_id"),
                        rs.getInt("quiz_id"),
                        rs.getInt("question_id"),
                        rs.getObject("user_choice_id", Integer.class),
                        null,
                        null
                ),
                quizId
        );

        for (QuizQuestion qq : quizQuestions) {
            Question question = questionDao.findById(qq.getQuestionId());
            List<Choice> choices = choiceDao.findByQuestion(qq.getQuestionId());
            qq.setQuestion(question);
            qq.setChoices(choices);
        }

        return quizQuestions;
    }

    public int create(QuizQuestion qq) {
        return jdbcTemplate.update(
                "INSERT INTO QuizQuestion (quiz_id, question_id, user_choice_id) VALUES (?, ?, ?)",
                qq.getQuizId(), qq.getQuestionId(), qq.getUserChoiceId()
        );
    }
    public void assignRandomQuestionsToQuiz(int quizId, int categoryId) {
        List<Integer> questionIds = jdbcTemplate.queryForList(
                "SELECT question_id FROM Question WHERE category_id = ? ORDER BY RAND() LIMIT 3", Integer.class, categoryId);

        for (Integer qId : questionIds) {
            jdbcTemplate.update("INSERT INTO QuizQuestion (quiz_id, question_id) VALUES (?, ?)", quizId, qId);
        }
    }

    public void updateUserChoice(int quizId, int questionId, int choiceId) {
        jdbcTemplate.update("UPDATE QuizQuestion SET user_choice_id = ? WHERE quiz_id = ? AND question_id = ?",
                choiceId, quizId, questionId);
    }



    public List<QuizQuestion> findDetailedByQuiz(int quizId) {
        List<QuizQuestion> quizQuestions = jdbcTemplate.query(
                "SELECT * FROM QuizQuestion WHERE quiz_id = ?",
                (rs, rowNum) -> {
                    int questionId = rs.getInt("question_id");
                    Integer userChoiceId = rs.getObject("user_choice_id", Integer.class);

                    // Build QuizQuestion object
                    QuizQuestion qq = new QuizQuestion();
                    qq.setQqId(rs.getInt("qq_id"));
                    qq.setQuizId(quizId);
                    qq.setQuestionId(questionId);
                    qq.setUserChoiceId(userChoiceId);

                    // Attach full question details
                    Question question = questionDao.findById(questionId);
                    qq.setQuestion(question);

                    // Attach full choices list
                    List<Choice> choices = choiceDao.findByQuestion(questionId);
                    qq.setChoices(choices);

                    return qq;
                },
                quizId
        );

        return quizQuestions;
    }








}
