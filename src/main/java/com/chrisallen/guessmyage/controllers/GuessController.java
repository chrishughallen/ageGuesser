package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.Guess;
import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuessController {

    UserService userSvc;
    UsersRepository usersRepo;
    GuessRepository guessRepo;

    public GuessController(UserService userSvc, UsersRepository usersRepo, GuessRepository guessRepo) {
        this.userSvc = userSvc;
        this.usersRepo = usersRepo;
        this.guessRepo = guessRepo;
    }

    @GetMapping("/guess")
    public String getGuess(Model model){
        model.addAttribute("user", userSvc.currentUser());
        model.addAttribute("guess", new Guess());
        return "/guess";
    }

    @PostMapping("/guess")
    public void checkGuess(@ModelAttribute Guess guess, @ModelAttribute User user){
        guess.setUser(usersRepo.findById(user.getId()));
        int age = userSvc.currentUser().getAge();
        if(guess.getAge() == age){
            System.out.println("CORRECT!");
        }
        guessRepo.save(guess);
        System.out.println(guess.getAge());
    }


}
