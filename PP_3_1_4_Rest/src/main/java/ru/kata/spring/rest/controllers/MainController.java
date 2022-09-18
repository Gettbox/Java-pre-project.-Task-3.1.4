package ru.kata.spring.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.rest.service.UserService;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String showMainPage(Model model, Principal principal) {
        model.addAttribute("authorizedUser", userService.getUserByUsername(principal.getName()));
        return "admin";
    }

    @PostMapping("/admin")
    public String redirectToMainPage() {
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUserPage(Model model, Principal principal) {
        model.addAttribute("authorizedUser", userService.getUserByUsername(principal.getName()));
        return "user";
    }

    @PostMapping("/user")
    public String redirectToUserPage() {
        return "redirect:/user";
    }
}