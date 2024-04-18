package ru.kata.spring.boot_security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Controller
//@RequestMapping("/admin")
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

       // System.out.println("usernameMy: " + newUser.getUsername());
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

   /* @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-panel";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("userId", userService.showUserById(id));
        return "id";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showUserById(id));
        model.addAttribute("allRoles", userService.getAllRoles());
        return "edit";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }*/

}
