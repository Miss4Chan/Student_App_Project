package com.example.studentmap.service.impl;

import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.enums.Role;
import com.example.studentmap.model.User;
import com.example.studentmap.model.exceptions.InvalidArgumentsException;
import com.example.studentmap.model.exceptions.PasswordsDoNotMatchException;
import com.example.studentmap.model.exceptions.UsernameAlreadyExistsException;
import com.example.studentmap.repository.UserRepository;
import com.example.studentmap.service.FavouritesService;
import com.example.studentmap.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final FavouritesService favouritesService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, FavouritesService favouritesService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.favouritesService = favouritesService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,Role.ROLE_USER);
        Favourites f = favouritesService.createFavourites(username);
        return userRepository.save(user);
    }
}
