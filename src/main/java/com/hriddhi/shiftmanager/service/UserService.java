package com.hriddhi.shiftmanager.service;


import com.hriddhi.shiftmanager.Users;
import com.hriddhi.shiftmanager.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping
    public Users addUser(Users user) {
        return userRepository.save(user);
    }
    @GetMapping
    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}
