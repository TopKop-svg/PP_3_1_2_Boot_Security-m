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
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



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
        List<User> list = userService.getAllUsers();
        User currentUser = userService.findByUsername(authenticatedUser.getUsername());
        System.out.println("текущий пользователь: "+currentUser);
        model.addAttribute("listUsers", list);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newUser", new User());
        model.addAttribute("listRoles", userService.getAllRoles());
        return "admin-panel";
    }

    @PostMapping("/admin/save")
    public String createNewUser(@ModelAttribute("newUser") User newUser,
                                @RequestParam(value = "selectedRolesNewUser", required = false) String[] selectedRolesNewUser){
        Logger logger = LoggerFactory.getLogger(AdminController.class);
        if (selectedRolesNewUser != null) {
            Set<Role> roles = new HashSet<>();
            for (String elemArrSelectedRoles : selectedRolesNewUser) {
                roles.add(userService.getRoleByName(elemArrSelectedRoles));
            }
            newUser.setRoles(roles);
        }
        logger.info("Saving new user: {}", newUser);

        userService.saveUser(newUser);
        return "redirect:/admin";
    }
    @PostMapping("/admin/update")
    public String update(
            @ModelAttribute("user") User user,
            @RequestParam(value = "selectedRoles", required = false) String[] selectedRoles
    ){
        if (selectedRoles != null) {
            Set<Role> roles = new HashSet<>();
            for (String elemArrSelectedRoles : selectedRoles) {
                roles.add(userService.getRoleByName(elemArrSelectedRoles));
            }
            user.setRoles(roles);
        }
        userService.updateUserById(user.getId(), user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete_user")
    public String deleteUser (@RequestParam(value = "id") int id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


}
