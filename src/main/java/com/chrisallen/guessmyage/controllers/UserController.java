package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.Profile;
import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.ProfileRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.ProfileService;
import com.chrisallen.guessmyage.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UsersRepository usersRepo;
    private UserService userSvc;
    private PasswordEncoder passwordEncoder;
    private GuessRepository guessRepo;
    private ProfileRepository profileRepo;
    private ProfileService profileSvc;



    public UserController(ProfileService profileSvc, ProfileRepository profileRepo, GuessRepository guessRepo, UsersRepository usersRepository, UserService userSvc, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepository;
        this.userSvc = userSvc;
        this.passwordEncoder = passwordEncoder;
        this.guessRepo = guessRepo;
        this.profileRepo = profileRepo;
        this.profileSvc = profileSvc;
    }


    @GetMapping("/")
    public String home(Model model) {
        if(!userSvc.isLoggedIn()){
            model.addAttribute("loggedIn", true);
        }
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
    public String newProfile(Model model){
        if(!userSvc.isLoggedIn()){
            return "redirect:/login";
        }
        if(profileSvc.hasProfile()){
            return "redirect:/myprofile";
        }
        model.addAttribute("profile", new Profile());
        return "/profile";
    }



    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute Profile profile){
        profile.setUser(userSvc.currentUser());
        profileRepo.save(profile);
        return"redirect:/myprofile";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable Long id, Model model){
        model.addAttribute("profile", profileRepo.findOne(id));
        return "/viewUser";
    }

    @GetMapping("/myprofile")
    public String showProfile(Model model){
        model.addAttribute("profile", profileRepo.findByUser(userSvc.currentUser()));
        model.addAttribute("user", userSvc.currentUser());
        return "/myprofile";
    }


    @GetMapping("/editprofile")
    public String editProfile(Model model){
        model.addAttribute("profile", profileRepo.findByUser(userSvc.currentUser()));
        return "/editprofile";
    }

    @PostMapping("/editprofile")
    public String saveProfileEdit(@ModelAttribute Profile profile){
        profileRepo.save(profile);
        return "redirect:/profile";
    }



//    @GetMapping("/guess")
//    public String guessGet(Model model){
//        model.addAttribute("profile", profileRepo.findByUser(userSvc.currentUser()));
//        model.addAttribute("user", userSvc.currentUser());
//        model.addAttribute("guess", new Guess());
//        return "/guess";
//    }
//
//    @PostMapping("/guess")
//    public String guessShow(@ModelAttribute Guess guess){
//        guess.setUser(userSvc.currentUser());
//        guessRepo.save(guess);
//        System.out.println(guess.getUser().getUsername());
//        System.out.println(guess.getAge());
//        return"redirect:/guess";
//    }






}
