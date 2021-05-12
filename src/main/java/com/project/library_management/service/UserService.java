package com.project.library_management.service;

import com.project.library_management.model.User;
import com.project.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
    return userRepository.findAll();
    }

    public User insertUser(User newUser) {
    return userRepository.save(newUser);
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }
}
