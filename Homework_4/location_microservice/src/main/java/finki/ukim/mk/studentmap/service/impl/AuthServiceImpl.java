package finki.ukim.mk.studentmap.service.impl;


import finki.ukim.mk.studentmap.model.User;
import finki.ukim.mk.studentmap.model.enums.Role;
import finki.ukim.mk.studentmap.model.exceptions.InvalidArgumentsException;
import finki.ukim.mk.studentmap.model.exceptions.InvalidUserCredentialsException;
import finki.ukim.mk.studentmap.model.exceptions.PasswordsDoNotMatchException;
import finki.ukim.mk.studentmap.model.exceptions.UsernameAlreadyExistsException;
import finki.ukim.mk.studentmap.repository.UserRepository;
import finki.ukim.mk.studentmap.service.AuthService;
import finki.ukim.mk.studentmap.service.FavouritesService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavouritesService favouritesService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, FavouritesService favouritesService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.favouritesService = favouritesService;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
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
        User user = new User(username,passwordEncoder.encode(password),name,surname, Role.ROLE_USER);
        return userRepository.save(user);
    }
}