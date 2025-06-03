package org.example.project1.controller;

import java.util.List;
import java.util.Map;


import org.example.project1.dao.QuizQuestionDao;
import org.example.project1.model.QuizQuestion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quiz-questions")
public class QuizQuestionController {

    private final QuizQuestionDao quizQuestionDao;

    public QuizQuestionController(QuizQuestionDao quizQuestionDao) {
        this.quizQuestionDao = quizQuestionDao;
    }

    @GetMapping("/quiz/{quizId}")
    public String listQuestionsByQuiz(@PathVariable int quizId, Model model) {
        model.addAttribute("quizQuestions", quizQuestionDao.findByQuiz(quizId));
        return "quiz-question-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("quizQuestion", new QuizQuestion());
        return "quiz-question-form";
    }

    @PostMapping("/save")
    public String saveQuizQuestion(@ModelAttribute QuizQuestion quizQuestion) {
        quizQuestionDao.create(quizQuestion);
        return "redirect:/quizzes";
    }
}
