package org.example.project1.controller;



import org.example.project1.dao.UserDao;
import org.example.project1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

//    @GetMapping
//    public String listUsers(Model model) {
//        model.addAttribute("users", userDao.findAll());
//        return "user-list";
//    }
@GetMapping
public String listUsers(@RequestParam(defaultValue = "1") int page, Model model) {
    int pageSize = 5;
    var allUsers = userDao.findAll();
    int totalUsers = allUsers.size();
    int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

    int fromIndex = (page - 1) * pageSize;
    int toIndex = Math.min(fromIndex + pageSize, totalUsers);

    var paginatedUsers = allUsers.subList(fromIndex, toIndex);

    model.addAttribute("users", paginatedUsers);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", totalPages);

    return "user-list";
}








    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userDao.create(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userDao.update(user);
//        return "redirect:/users";
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userDao.delete(id);
        return "redirect:/admin";
//        return "redirect:/users";
    }
    @PostMapping("/toggleStatus/{id}")
    public String toggleUserStatus(@PathVariable int id) {
        User user = userDao.findById(id);
        user.setActive(!user.isActive());  // flip active status
        userDao.update(user);  // reuse your existing update
//        return "redirect:/users";
        return "redirect:/admin";
    }


}

