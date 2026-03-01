package com.schooldiary.service;

import com.schooldiary.model.User;
import com.schooldiary.repository.UserRepository;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String login, String password) {

        User user = userRepository.findByLogin(login);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}