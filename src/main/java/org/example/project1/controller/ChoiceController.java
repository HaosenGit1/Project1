package org.example.project1.controller;



import org.example.project1.dao.ChoiceDao;
import org.example.project1.model.Choice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/choices")
public class ChoiceController {

    private final ChoiceDao choiceDao;

    public ChoiceController(ChoiceDao choiceDao) {
        this.choiceDao = choiceDao;
    }

    @GetMapping("/question/{questionId}")
    public String listChoicesByQuestion(@PathVariable int questionId, Model model) {
        model.addAttribute("choices", choiceDao.findByQuestion(questionId));
        return "choice-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("choice", new Choice());
        return "choice-form";
    }

    @PostMapping("/save")
    public String saveChoice(@ModelAttribute Choice choice) {
        choiceDao.create(choice);
        return "redirect:/questions";
    }
}

