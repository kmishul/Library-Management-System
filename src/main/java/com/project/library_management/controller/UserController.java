package com.project.library_management.controller;

import com.project.library_management.exception.UserNotFoundException;
import com.project.library_management.model.User;
import com.project.library_management.service.UserService;
import com.project.library_management.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    private final static Logger LOGGER =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private UserService userService;

    //Get all the users
    @GetMapping("/users")
    List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    //Create new user
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) throws Exception {
        if(UserValidator.isValidUser(newUser))
            return userService.insertUser(newUser);
        else throw new Exception();
    }

    //Get user by given id
    @GetMapping("/users/{id}")
    User findOne(@PathVariable int id) {
        LOGGER.info("/users/{id} called with id "+ id);

        return userService.findUserById(id)                 //lambda expression
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
