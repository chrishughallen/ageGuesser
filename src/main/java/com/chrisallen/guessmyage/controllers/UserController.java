package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.Guess;
import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    private UsersRepository usersRepo;
    private UserService userSvc;
    private GuessRepository guessRepo;
    private PasswordEncoder passwordEncoder;

    Date now = new Date();
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);




    public UserController(UsersRepository usersRepository, UserService userSvc, GuessRepository guessRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepository;
        this.userSvc = userSvc;
        this.guessRepo = guessRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/randomGuess";
    }

    @GetMapping("/welcome")
    public String loggedIn(Model model){
        model.addAttribute("user", userSvc.currentUser());
        model.addAttribute("age", userSvc.getUserAge(userSvc.currentUser()));
        return "/welcome";
    }


    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) throws ParseException {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setUsername(user.getEmail());
        usersRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable long id, Model model){
        model.addAttribute("user", usersRepo.findById(id));
        model.addAttribute("age", userSvc.getUserAge(usersRepo.findById(id)));
        model.addAttribute("Guess", new Guess());
        return "/userPage";
    }

    @PostMapping("/guess/{id}")
    public String checkGuess(@PathVariable long id, @ModelAttribute Guess guess, Model model ){
        User user = usersRepo.findById(id);
        guess.setUser(usersRepo.findById(id));
        guessRepo.save(guess);
        if(guess.getAge() == userSvc.getUserAge(user)){
            return "/correct";
        }
        return "/incorrect";
    }

    @GetMapping("/randomGuess")
    public String getRandomUser(){
        User user = usersRepo.findById(userSvc.getRandomUserId());
        return "redirect:/user/" + user.getId();
    }

}
