package ru.kata.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;


@Slf4j
@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String firstPage() {
        return "login";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping(value = "/admin")
    public String index(ModelMap model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("user",  userService.findByUsername(authenticatedUser.getUsername()));
        model.addAttribute("newUser", new User());
        return "admin-panel";
    }


    @PostMapping("/admin/save")
    public String createNewUser(@ModelAttribute("newUser") User newUser){
        userService.saveUser(newUser);
        log.info("New user: " + newUser.getUsername());
        return "redirect:/admin";
    }

    @PostMapping("/admin/update")
    public String update(
            @ModelAttribute("user") User user){
        userService.updateUserById(user.getId(), user);
        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/delete_user")
    public String deleteUser (@RequestParam(value = "id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


}

