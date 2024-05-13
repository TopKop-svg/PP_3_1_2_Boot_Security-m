package ru.kata.spring.boot_security.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Slf4j
@RestController
@RequestMapping("/userApi")
public class UserController {
    @GetMapping("/auth")
    public ResponseEntity<User> getAuthUser(@AuthenticationPrincipal User user) {
        log.info("current user : " + user.getUsername() + "\n user email: " + user.getEmail() );
        return ResponseEntity.ok(user);
    }



}
