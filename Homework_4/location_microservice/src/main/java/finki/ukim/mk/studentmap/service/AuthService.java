package finki.ukim.mk.studentmap.service;

import finki.ukim.mk.studentmap.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);
    UserDetails loadUserByUsername(String username);
}

