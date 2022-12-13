package com.example.studentmap.service;

import com.example.studentmap.model.User;

public interface AuthService {
    User login(String username, String password);
}
