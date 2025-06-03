package org.example.project1.controller;


import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import org.example.project1.dao.QuestionDao;
import org.example.project1.model.Question;
import org.example.project1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionDao questionDao;

    public QuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @GetMapping("/category/{categoryId}")
    public String listQuestionsByCategory(@PathVariable int categoryId, Model model) {
        model.addAttribute("questions", questionDao.findByCategory(categoryId));
        return "question-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("question", new Question());
        return "question-form";
    }

//    @PostMapping("/save")
//    public String saveQuestion(@ModelAttribute Question question) {
//        questionDao.create(question);
//        return "redirect:/categories";
//    }

    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute Question question, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.isAdmin()) return "redirect:/login";

        if (question.getQuestionId() > 0) {
            questionDao.update(question);
        } else {
            questionDao.create(question);
        }
        return "redirect:/questions/all";
    }


    @GetMapping("/all")
    public String listAllQuestions(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.isAdmin()) return "redirect:/login";

        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);
        return "admin-question-list";


    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.isAdmin()) return "redirect:/login";

        Question question = questionDao.findById(id);
        model.addAttribute("question", question);
        return "question-form";
    }

    @GetMapping("/suspend/{id}")
    public String suspendQuestion(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.isAdmin()) return "redirect:/login";

        questionDao.suspend(id);
//        return "redirect:/questions/all";
        return "redirect:/admin/questions";

    }

    @GetMapping("/activate/{id}")
    public String activateQuestion(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.isAdmin()) return "redirect:/login";

        questionDao.activate(id);
//        return "redirect:/questions/all";
        return "redirect:/admin/questions";

    }


    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.isAdmin()) return "redirect:/login";

        questionDao.delete(id);
//        return "redirect:/questions/all";
        return "redirect:/admin/questions";

    }
















}

