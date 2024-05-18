package ru.kata.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Collection;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/adminApi")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getTest(@PathVariable int id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("user/{id}")
    public ResponseEntity<HttpStatus> editUser(@PathVariable int id, @RequestBody User user) {
        userService.updateUserById(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Collection<Role>> getMyRoles(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.findById(id).getRoles(), HttpStatus.OK);
    }


   /* @GetMapping("/")
    public String firstPage() {
        return "login";
    }*/

   /* @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }*/

    /*@RequestMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }*/



    /*@GetMapping(value = "/admin")
    public String index(ModelMap model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("user",  userService.findByUsername(authenticatedUser.getUsername()));
        model.addAttribute("newUser", new User());

        return "admin-panel-rest";
    }*/





   /* @DeleteMapping(value = "api/admin/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.deleteUserById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }*/

}

