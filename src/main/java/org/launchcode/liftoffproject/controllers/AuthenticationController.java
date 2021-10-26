package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.auth.EmailExistsException;
import org.launchcode.liftoffproject.auth.UserService;
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
    public String register(@ModelAttribute LoginRegisterFormDTO loginRegisterFormDTO, Errors errors) {

        if (errors.hasErrors())
            return "register";

        try {
            userService.save(loginRegisterFormDTO);
        } catch (EmailExistsException e) {
            errors.rejectValue("username", "username.alreadyexists", e.getMessage());
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(Model model, Principal user, String error, String logout) {

        if (user!= null)
            return "redirect:/";

        return "login";
    }

}