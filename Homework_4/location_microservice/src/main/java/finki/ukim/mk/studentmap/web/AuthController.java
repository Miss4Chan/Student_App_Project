package finki.ukim.mk.studentmap.web;

import finki.ukim.mk.studentmap.model.User;
import finki.ukim.mk.studentmap.model.exceptions.InvalidArgumentsException;
import finki.ukim.mk.studentmap.model.exceptions.InvalidUserCredentialsException;
import finki.ukim.mk.studentmap.model.exceptions.PasswordsDoNotMatchException;
import finki.ukim.mk.studentmap.model.exceptions.UsernameAlreadyExistsException;
import finki.ukim.mk.studentmap.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "mapa";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try {
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/locations";
        } catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasLoginError", true);
            model.addAttribute("loginError", exception.getMessage());
            return "mapa";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/locations";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname) {
        try {
            this.authService.register(username, password, repeatedPassword, name, surname);
            return "redirect:/locations";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException exception) {
            return "redirect:/locations?registerError=" + exception.getMessage();
        }
    }

}
