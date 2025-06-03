package org.example.project1.controller;


import java.util.List;
import java.util.Map;




import jakarta.servlet.http.HttpSession;
import org.example.project1.dao.QuizDao;
import org.example.project1.dao.QuizQuestionDao;
import org.example.project1.model.QuizQuestion;
import org.example.project1.model.Quiz;
import org.example.project1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizDao quizDao;
    private final QuizQuestionDao quizQuestionDao;  // ADD THIS


    public QuizController(QuizDao quizDao, QuizQuestionDao quizQuestionDao) {
        this.quizDao = quizDao;
        this.quizQuestionDao = quizQuestionDao;
    }

    @GetMapping("/user/{userId}")
    public String listQuizzesByUser(@PathVariable int userId, Model model) {
        model.addAttribute("quizzes", quizDao.findByUser(userId));
        return "quiz-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "quiz-form";
    }

    @PostMapping("/save")
    public String saveQuiz(@ModelAttribute Quiz quiz) {
        quizDao.create(quiz);
        return "redirect:/quizzes";
    }



    @GetMapping("/start/{categoryId}")
    public String startQuiz(@PathVariable int categoryId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
//        if (user == null) {
//            return "redirect:/login";
//        }
        if (user == null) {
            System.out.println("❌ ERROR: loggedInUser is NULL when starting quiz!");
            return "redirect:/login";
        } else {
            System.out.println("✔ Starting quiz for userId: " + user.getUserId() + ", email: " + user.getEmail());
        }

        int quizId = quizDao.createNewQuizForUser(user.getUserId(), categoryId);
        quizQuestionDao.assignRandomQuestionsToQuiz(quizId, categoryId);

//        return "redirect:/quizzes/take/" + quizId;
        return "redirect:/quizzes/take/" + quizId + "/question/0";

    }


//    @GetMapping("/take/{quizId}")
//    public String takeQuiz(@PathVariable int quizId, Model model) {
//        List<QuizQuestion> quizQuestions = quizQuestionDao.findDetailedByQuiz(quizId);
//        model.addAttribute("quizId", quizId);
//        model.addAttribute("quizQuestions", quizQuestions);
//        return "take-quiz";  // new JSP page you create
//    }

    @PostMapping("/submit/{quizId}")
    public String submitQuiz(@PathVariable int quizId, @RequestParam Map<String, String> allParams) {
        allParams.forEach((key, value) -> {
            if (key.startsWith("question_")) {
                int questionId = Integer.parseInt(key.substring(9));
                int choiceId = Integer.parseInt(value);
                quizQuestionDao.updateUserChoice(quizId, questionId, choiceId);
            }
        });
        quizDao.markQuizCompleted(quizId);
        return "redirect:/quizzes/results/" + quizId;
    }

//    @GetMapping("/results/{quizId}")
//    public String showResults(@PathVariable int quizId, Model model) {
//        Quiz quiz = quizDao.findById(quizId);
//        List<QuizQuestion> questions = quizQuestionDao.findDetailedByQuiz(quizId);
//
//        long correctCount = questions.stream().filter(QuizQuestion::isCorrect).count();
//        boolean passed = correctCount >= 2;
//
//        model.addAttribute("quiz", quiz);
//        model.addAttribute("questions", questions);
//        model.addAttribute("passed", passed);
//        return "quiz-results";
//    }

    @GetMapping("/take/{quizId}")
    public String takeQuiz(@PathVariable int quizId, Model model) {
        List<QuizQuestion> quizQuestions = quizQuestionDao.findByQuiz(quizId);
        model.addAttribute("quizQuestions", quizQuestions);
        model.addAttribute("quizId", quizId);
        return "take-quiz";
    }

    @GetMapping("/take/{quizId}/question/{index}")
    public String takeQuizQuestion(@PathVariable int quizId,
                                   @PathVariable int index,
                                   Model model,
                                   HttpSession session) {

        List<QuizQuestion> quizQuestions = quizQuestionDao.findByQuiz(quizId);

        if (index >= quizQuestions.size()) {
            return "redirect:/quizzes/complete/" + quizId;
        }

        QuizQuestion currentQuestion = quizQuestions.get(index);

        model.addAttribute("quizId", quizId);
        model.addAttribute("question", currentQuestion);
        model.addAttribute("index", index);
        model.addAttribute("totalQuestions", quizQuestions.size());

        return "take-quiz-question";
    }




    @PostMapping("/take/{quizId}/question/{index}")
    public String submitQuizAnswer(@PathVariable int quizId,
                                   @PathVariable int index,
                                   @RequestParam int choiceId) {

        List<QuizQuestion> quizQuestions = quizQuestionDao.findByQuiz(quizId);

        if (index >= quizQuestions.size()) {
            return "redirect:/quizzes/complete/" + quizId;
        }

        QuizQuestion currentQuestion = quizQuestions.get(index);

        // Store user’s choice immediately in DB
        quizQuestionDao.updateUserChoice(quizId, currentQuestion.getQuestionId(), choiceId);

        if (index + 1 >= quizQuestions.size()) {
            // Last question → finish quiz
            return "redirect:/quizzes/complete/" + quizId;
        }

        // Go to next question
        return "redirect:/quizzes/take/" + quizId + "/question/" + (index + 1);
    }


    @GetMapping("/complete/{quizId}")
    public String completeQuiz(@PathVariable int quizId) {

        quizDao.markQuizCompleted(quizId);

        return "redirect:/quizzes/results/" + quizId;
    }

    @GetMapping("/results/{quizId}")
    public String showResults(@PathVariable int quizId, Model model) {
        Quiz quiz = quizDao.findById(quizId);
        List<QuizQuestion> questions = quizQuestionDao.findDetailedByQuiz(quizId);

        long correctCount = questions.stream().filter(QuizQuestion::isCorrect).count();
        int totalQuestions = questions.size();
        double percent = (correctCount * 100.0) / totalQuestions;
        boolean passed = percent >= 60.0;

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalQuestions", totalQuestions);
        model.addAttribute("percent", percent);
        model.addAttribute("passed", passed);

        return "quiz-results";
    }







}
