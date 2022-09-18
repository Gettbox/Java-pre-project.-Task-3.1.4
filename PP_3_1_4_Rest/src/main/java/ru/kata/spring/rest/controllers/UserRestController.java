package ru.kata.spring.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.rest.models.User;
import ru.kata.spring.rest.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> showUser(Principal principal) {
        return new ResponseEntity<>(userService.getUserByUsername(principal.getName()), HttpStatus.OK);
    }
}