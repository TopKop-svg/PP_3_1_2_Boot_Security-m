package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;

@Controller
public class BaseController {
    @GetMapping("/admin")
    public String showAllUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin";
    }
    @GetMapping("/user")
    public String showOneUser() {
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/")
    public String emptyPage() {
        return "login";
    }
}
