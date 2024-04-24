package ru.kata.spring.boot_security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

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
        //User currentUser = userService.findByUsername(authenticatedUser.getUsername());
        model.addAttribute("listUsers", userService.getAllUsers());
        /* model.addAttribute("listRoles", roleService.getAllRoles());*/
        model.addAttribute("user",  userService.findByUsername(authenticatedUser.getUsername()));
        //model.addAttribute("newUser", new User());
        //model.addAttribute("listRoles", roleService.getAllRoles());
        return "admin-panel";
    }


    @PostMapping("/admin/save")
    public String createNewUser(@ModelAttribute("user") User newUser
    , @RequestParam(value = "selectedRolesNewUser", required = false) String role){
        userService.saveUser(newUser);
        return "redirect:/admin";
    }
    @PostMapping("/admin/update")
    public String update(
            @ModelAttribute("user") User user
            ){
        userService.updateUserById(user.getId(), user);
        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/delete_user")
    public String deleteUser (@RequestParam(value = "id") int id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


}
