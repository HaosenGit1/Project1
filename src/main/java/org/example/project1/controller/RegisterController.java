package org.example.project1.controller;

import org.example.project1.dao.UserDao;
import org.example.project1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserDao userDao;

    public RegisterController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/save")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userDao.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(true);
        user.setAdmin(false);
        userDao.create(user);
        return "redirect:/login";
    }
}
