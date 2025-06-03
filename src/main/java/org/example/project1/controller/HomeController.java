//package org.example.project1.controller;
//
//
//
//import jakarta.servlet.http.HttpSession;
//import org.example.project1.model.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class HomeController {
//
//    @GetMapping("/home")
//    public String home(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (user == null) {
//            return "redirect:/login";  // force to login if not logged in
//        }
//        model.addAttribute("user", user);
//        return "home";  // will render /WEB-INF/jsp/home.jsp
//    }
//}

package org.example.project1.controller;

import jakarta.servlet.http.HttpSession;
import org.example.project1.dao.CategoryDao;
import org.example.project1.dao.QuizDao;
import org.example.project1.model.Category;
import org.example.project1.model.Quiz;
import org.example.project1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CategoryDao categoryDao;
    private final QuizDao quizDao;

    public HomeController(CategoryDao categoryDao, QuizDao quizDao) {
        this.categoryDao = categoryDao;
        this.quizDao = quizDao;
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Category> categories = categoryDao.findAll();
        List<Quiz> recentQuizzes = quizDao.findByUser(user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        model.addAttribute("recentQuizzes", recentQuizzes);

        return "home";
    }
}
