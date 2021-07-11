package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.UserData;
import org.launchcode.liftoffproject.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("signup")
    public String displayUserSignup(Model model) {
        model.addAttribute("title", "New User Sign-Up");
        model.addAttribute(new User());
        return "user/signup";
    }

    @PostMapping("signup")
    public String processUserSignup(@ModelAttribute User newUser) {
        UserData.add(newUser);
        return "user/success";
    }

}
