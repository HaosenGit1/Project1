package org.example.project1.controller;


import org.example.project1.dao.UserDao;
import org.example.project1.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserDao userDao;

    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String showLoginForm() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        User user = userDao.findByEmail(email);
        if (user == null || !new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }

        if (!user.isActive()) {
            model.addAttribute("error", "Your account has been suspended. Please contact support.");
            return "login";
        }
        session.setAttribute("loggedInUser", user);

        System.out.println("✔ Login successful: " + user.getEmail() + ", userId: " + user.getUserId());
        System.out.println("✔ Session attribute set: loggedInUser");
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("✔ Logging out user, invalidating session");

        session.invalidate();
        return "redirect:/login";
    }
}
