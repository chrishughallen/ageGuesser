package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UsersRepository usersRepo;
    private UserService userSvc;
    private PasswordEncoder passwordEncoder;



    public UserController(UsersRepository usersRepository, UserService userSvc,  PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepository;
        this.userSvc = userSvc;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/welcome")
    public String loggedIn(){

        return "/welcome";
    }


    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        User currentUser = userSvc.currentUser();
        model.addAttribute("user", currentUser);
        return "/profile";
    }

}
