package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.ScoreRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.GuessService;
import com.chrisallen.guessmyage.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class UserController {

    private UsersRepository usersRepo;
    private UserService userSvc;
    private GuessService guessSvc;
    private GuessRepository guessRepo;
    private ScoreRepository scoreRepo;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepository usersRepository, UserService userSvc, GuessRepository guessRepo, ScoreRepository scoreRepo, GuessService guessSvc, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepository;
        this.userSvc = userSvc;
        this.guessRepo = guessRepo;
        this.scoreRepo = scoreRepo;
        this.passwordEncoder = passwordEncoder;
        this.guessSvc = guessSvc;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("loggedIn", userSvc.isLoggedIn());
        return "about";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("loggedIn", userSvc.isLoggedIn());
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid User user, Errors errors, Model model, @RequestParam ("email") String username, @RequestParam("passwordConfirm") String passwordConfirm, @RequestParam (required = false, name = "gender") String gender) throws ParseException {

        if(userSvc.getUserAge(user)<18){
            model.addAttribute("user", user);
            model.addAttribute("underAge", true);
            return "redirect:/register";
        }

        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("errors", errors);
            return "redirect:/register";
        }
        if(user.getDob() == null){
            model.addAttribute("user", user);
            model.addAttribute("noDob", true);
            return "redirect:/register";
        }
        if(!user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordMatchError", true);
            model.addAttribute("user", user);
            return "redirect:/register";
        }
        if(userSvc.userExists(username)){
            model.addAttribute("userExists", true);
            model.addAttribute("user", user);
            return "redirect:/register";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setUsername(user.getEmail());
        user.setGender(gender);
        usersRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/welcome")
    public String loggedIn(Model model){
        User user = userSvc.currentUser();
        model.addAttribute("loggedIn", userSvc.isLoggedIn());
        model.addAttribute("user", user);
        model.addAttribute("age", userSvc.getUserAge(user));
        model.addAttribute("totalGuesses", guessSvc.getUserTotalGuesses(user).size());
        model.addAttribute("correctGuesses", guessSvc.getUserCorrectGuesses(guessSvc.getUserTotalGuesses(user)));
        model.addAttribute("totalGuessesOfUser", guessSvc.totalGuessesOfUsersAge(user));
        if(guessSvc.totalGuessesOfUsersAge(user)>0){
            model.addAttribute("howOldUserLooks", guessSvc.findUsersAverageAge(user));
        }
        return "welcome";
    }

    @GetMapping("/about")
    public String landingPage(){
        return"redirect:/";
    }

    @PostMapping("/editPicture")
    public String editPic(@RequestParam ("picUrl") String photo){
        userSvc.currentUser().setPhoto(photo);
        usersRepo.save(userSvc.currentUser());
        return"redirect:/welcome";
    }
}