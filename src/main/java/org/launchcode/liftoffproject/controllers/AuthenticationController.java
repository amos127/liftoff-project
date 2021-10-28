package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.auth.EmailExistsException;
import org.launchcode.liftoffproject.auth.UserAlreadyExistsAuthenticationException;
import org.launchcode.liftoffproject.auth.UserService;
import org.launchcode.liftoffproject.data.UserRepository;
import org.launchcode.liftoffproject.models.User;
import org.launchcode.liftoffproject.models.dto.LoginRegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("user")
    public User getLoggedInUser(Principal principal) {
        if (principal != null)
            return userService.findByUsername(principal.getName());
        return null;
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new LoginRegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute LoginRegisterFormDTO loginRegisterFormDTO, Errors errors,
                           Model model) {

        User existingUser = userRepository.findByUsername(loginRegisterFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = loginRegisterFormDTO.getPassword();
        String verifyPassword = loginRegisterFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }
        userService.save(loginRegisterFormDTO);
        return "redirect:/";

    }

    @GetMapping(value = "/login")
    public String login(@ModelAttribute LoginRegisterFormDTO loginRegisterFormDTO,
                        Model model, Principal user, Errors errors) {

        if (user != null)
            return "redirect:/";

        return "login";
    }

}