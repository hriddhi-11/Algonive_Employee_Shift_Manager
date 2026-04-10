package com.hriddhi.shiftmanager.service;


import com.hriddhi.shiftmanager.Users;
import com.hriddhi.shiftmanager.UserRepository;
import com.hriddhi.shiftmanager.exception.BadRequestException;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users addUser(Users user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
           throw new BadRequestException("user already exists");
        }
        return userRepository.save(user);
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

}
