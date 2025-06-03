package org.example.project1.controller;



import org.example.project1.dao.ContactDao;
import org.example.project1.model.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private final ContactDao contactDao;

    public ContactController(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactDao.findAll());
        return "contact-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute Contact contact) {
        contactDao.create(contact);
        return "redirect:/contacts";
    }
}
