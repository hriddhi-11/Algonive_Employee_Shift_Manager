package com.hriddhi.shiftmanager.controller;

import com.hriddhi.shiftmanager.Users;
import com.hriddhi.shiftmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Users addUser(@RequestBody Users user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<Users> getUsers() {
        return userService.getUsers();
    }
}