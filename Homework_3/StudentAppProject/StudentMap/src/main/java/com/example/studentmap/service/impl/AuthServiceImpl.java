package com.example.studentmap.service.impl;

import com.example.studentmap.model.User;
import com.example.studentmap.model.exceptions.InvalidArgumentsException;
import com.example.studentmap.model.exceptions.InvalidUserCredentialsException;
import com.example.studentmap.repository.UserRepository;
import com.example.studentmap.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }
}

