package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.Profile;
import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.ProfilesRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.ProfileService;
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
    private ProfilesRepository profilesRepo;
    private UserService userSvc;
    private ProfileService profileSvc;
    private PasswordEncoder passwordEncoder;


    public UserController(UsersRepository usersRepository, ProfilesRepository profilesRepo, UserService userSvc, ProfileService profileSvc, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepository;
        this.profilesRepo = profilesRepo;
        this.userSvc = userSvc;
        this.profileSvc = profileSvc;
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
        if(profileSvc.userHasProfile()){
            Profile profile = profilesRepo.findByUser(userSvc.currentUser());
            model.addAttribute("profile", profile);
            return "/profile";
        }
        return "redirect:/editProfile";
    }


    @GetMapping("/editProfile")
    public String editProfile(Model model){
        if(profileSvc.userHasProfile()){
            Profile profile = profilesRepo.findByUser(userSvc.currentUser());
            model.addAttribute("profile", profile);
            return "/editProfile";
        }
        model.addAttribute("profile", new Profile());
        return "/editProfile";
    }

    @PostMapping("/editProfile")
    public String saveProfile(@ModelAttribute Profile profile){
        profile.setUser(userSvc.currentUser());
        profilesRepo.save(profile);
        return "redirect:/profile";
    }




}
