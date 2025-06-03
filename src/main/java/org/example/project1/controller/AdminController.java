package org.example.project1.controller;



import jakarta.servlet.http.HttpSession;
import org.example.project1.dao.QuestionDao;
import org.example.project1.dao.QuizDao;
import org.example.project1.dao.UserDao;
import org.example.project1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDao userDao;
    private final QuestionDao questionDao;
    private final QuizDao quizDao;


    public AdminController(UserDao userDao, QuestionDao questionDao, QuizDao quizDao) {
        this.userDao = userDao;
        this.questionDao = questionDao;
        this.quizDao = quizDao;
    }
    private boolean isNotAdmin(User user) {
        return user == null || !user.isAdmin();
    }

    @GetMapping
    public String adminHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (isNotAdmin(user)) return "redirect:/login";

        model.addAttribute("userCount", userDao.findAll().size()); // Bonus stats
        return "admin-home";
    }

//    @GetMapping("/users")
//    public String adminUserManagement(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (isNotAdmin(user)) return "redirect:/login";
//
//        model.addAttribute("users", userDao.findAll());
//        return "admin-user-list";
//    }

    @GetMapping("/users")
    public String adminUserManagement(
            @RequestParam(defaultValue = "1") int page,
            HttpSession session,
            Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (isNotAdmin(user)) return "redirect:/login";

        int pageSize = 5;
        var allUsers = userDao.findAll();
        int totalUsers = allUsers.size();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalUsers);
        var paginatedUsers = allUsers.subList(fromIndex, toIndex);

        model.addAttribute("users", paginatedUsers);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", totalPages);

        return "admin-user-list";
    }


    @GetMapping("/quizzes")
    public String manageQuizzes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String sortBy,
            Model model,
            HttpSession session
    ) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || !admin.isAdmin()) return "redirect:/login";

        int pageSize = 5;
        var results = quizDao.findQuizResults(categoryId, userId, sortBy, page, pageSize);
        int totalCount = quizDao.countQuizResults(categoryId, userId);

        model.addAttribute("quizResults", results);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", (int) Math.ceil((double) totalCount / pageSize));
        return "admin-quiz-list";
    }











//    @GetMapping("/users/suspend/{id}")
//    public String suspendUser(@PathVariable int id, HttpSession session) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (isNotAdmin(user)) return "redirect:/login";
//
//        userDao.suspend(id);
//        return "redirect:/admin";
//// Go back to admin home
//    }
@GetMapping("/users/suspend/{id}")
public String suspendUser(@PathVariable int id, @RequestParam(defaultValue = "1") int page, HttpSession session) {
    User user = (User) session.getAttribute("loggedInUser");
    if (isNotAdmin(user)) return "redirect:/login";

    userDao.suspend(id);
    return "redirect:/admin/users?page=" + page;
}



    @GetMapping("/users/activate/{id}")
    public String activateUser(@PathVariable int id, @RequestParam(defaultValue = "1") int page, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (isNotAdmin(user)) return "redirect:/login";

        userDao.activate(id);
        return "redirect:/admin/users?page=" + page;
    }


//    @GetMapping("/users/activate/{id}")
//    public String activateUser(@PathVariable int id, HttpSession session) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (isNotAdmin(user)) return "redirect:/login";
//
//        userDao.activate(id);
//        return "redirect:/admin";  // Go back to admin home
//    }




    //  add quiz and question management mappings here

    @GetMapping("/questions")
    public String manageQuestions(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (isNotAdmin(user)) return "redirect:/login";

        var allQuestions = questionDao.findAll();
        model.addAttribute("questions", allQuestions);

        return "admin-question-list";
    }






}
