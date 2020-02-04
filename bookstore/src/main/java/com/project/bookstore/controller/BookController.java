package com.project.bookstore.controller;

import java.util.List;

import com.project.bookstore.models.Book;
import com.project.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.bookstore.models.User;
import com.project.bookstore.service.UserService;
import com.project.bookstore.service.SecurityService;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @ModelAttribute("categories")
    public List<String> getCategories() {
        return bookRepository.getCategories();
    }

    @RequestMapping("/")
    public String def(Model model) {
        return "redirect:/book";
    }

    @RequestMapping("/book")
    public String book(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String name, @RequestParam String description, @RequestParam String source, @RequestParam String cover, @RequestParam String category, @RequestParam String author) {
        String username = securityService.findLoggedInUsername();
        Book book = new Book(name, description, source, cover, category, author, username);
        bookRepository.save(book);
        return "redirect:/book";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElse(null));
        return "show";
    }

    @RequestMapping("/category/{category}")
    public String category(@PathVariable String category, Model model) {
        model.addAttribute("books", bookRepository.findByCategory(category));
        return "category";
    }

    @RequestMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("books", bookRepository.findByNameOrCategory(query));
        return "search";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String username, @RequestParam String password, @RequestParam String passwordConfirm, Model model) {
        if(username.length()<0){
            model.addAttribute("usererror", "Username Too Short");
            return "registration";
        }
        if(password.length()<0){
            model.addAttribute("passerror", "Password Too Short");
            return "registration";
        }
        if(!password.equals(passwordConfirm)){
            model.addAttribute("passerror", "Passwords Don't Match");
            return "registration";
        }
        if(userService.findByUsername(username)!=null){
            model.addAttribute("usererror", "Username Already Exists");
            return "registration";
        }
        User userForm = new User();
        userForm.setUsername(username);
        userForm.setPassword(password);
        userForm.setPasswordConfirm(passwordConfirm);
        userService.save(userForm);

        //securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/book";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}