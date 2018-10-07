package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.Guess;
import com.chrisallen.guessmyage.models.Score;
import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.ScoreRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private UsersRepository usersRepo;
    private UserService userSvc;
    private GuessRepository guessRepo;
    private ScoreRepository scoreRepo;
    private PasswordEncoder passwordEncoder;

    Date now = new Date();
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);




    public UserController(UsersRepository usersRepository,
                          UserService userSvc,
                          GuessRepository guessRepo,
                          ScoreRepository scoreRepo,
                          PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepository;
        this.userSvc = userSvc;
        this.guessRepo = guessRepo;
        this.scoreRepo = scoreRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String home(Model model) {

        if(userSvc.isLoggedIn()){
            model.addAttribute("loggedIn", true);
        }
        return "redirect:/randomGuess";
    }

    @GetMapping("/welcome")
    public String loggedIn(Model model){
        if(userSvc.isLoggedIn()){
            model.addAttribute("loggedIn", true);
        }
        model.addAttribute("user", userSvc.currentUser());
        model.addAttribute("age", userSvc.getUserAge(userSvc.currentUser()));
        List<Score> totalGuesses = scoreRepo.findAllByguesser_id(userSvc.currentUser().getId());
        model.addAttribute("totalGuesses", totalGuesses.size());
        int correctGuesses = 0;
        for (Score score : totalGuesses) {
            if(score.isCorrect()){
                correctGuesses ++;
            }
        }
        model.addAttribute("correctGuesses", correctGuesses);

        List<Guess>otherGuesses = guessRepo.findAllByUser_id(userSvc.currentUser().getId());
        int totalAges = 0;
        for (Guess guess : otherGuesses) {
            totalAges += guess.getAge();
        }
        System.out.println(totalAges);
        if(totalAges>0){
            model.addAttribute("howOldUserLooks", totalAges/otherGuesses.size());
        }
        model.addAttribute("totalGuessesOfUser", otherGuesses.size());
        return "/welcome";
    }


    @GetMapping("/register")
    public String showSignupForm(Model model){
        if(userSvc.isLoggedIn()){
            model.addAttribute("loggedIn", true);
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid User user,
                           Errors errors,
                           Model model,
                           @RequestParam ("email") String username) throws ParseException {
        if (errors.hasErrors() || user.getDob() == null) {
            model.addAttribute("errors", errors);
            model.addAttribute("user", user);
            model.addAttribute("noDob", true);
            return "/register";
        }

        if(userSvc.userExists(username)){
            model.addAttribute("userExists", true);
            model.addAttribute("user", user);
            return "/register";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setUsername(user.getEmail());
        usersRepo.save(user);
        return "redirect:/login";
    }



    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable long id, Model model){
        if(userSvc.isLoggedIn()){
            model.addAttribute("loggedIn", true);
            List<Score> totalGuesses = scoreRepo.findAllByguesser_id(userSvc.currentUser().getId());
            model.addAttribute("totalGuesses", totalGuesses.size());
            int correctGuesses = 0;
            for (Score score : totalGuesses) {
                if(score.isCorrect()){
                    correctGuesses ++;
                }
            }
            model.addAttribute("correctGuesses", correctGuesses);

            List<Guess>otherGuesses = guessRepo.findAllByUser_id(userSvc.currentUser().getId());
            int totalAges = 0;
            for (Guess guess : otherGuesses) {
                totalAges += guess.getAge();
            }
            System.out.println(totalAges);
            if(totalAges>0){
                model.addAttribute("howOldUserLooks", totalAges/otherGuesses.size());
            }
            model.addAttribute("totalGuessesOfUser", otherGuesses.size());
        }


        if(userSvc.isLoggedIn() && id == userSvc.currentUser().getId()){
            return "redirect:/welcome";
        }
        model.addAttribute("user", usersRepo.findById(id));
        model.addAttribute("age", userSvc.getUserAge(usersRepo.findById(id)));
        model.addAttribute("Guess", new Guess());
        return "/userPage";
    }











    @PostMapping("/guess/{id}")
    public String checkGuess(@PathVariable long id,
                             @ModelAttribute Guess guess,
                             Model model ){

        if(userSvc.isLoggedIn()){
            model.addAttribute("loggedIn", true);
        }

        if(userSvc.isLoggedIn()){
            Iterable<Score> scores = scoreRepo.findAll();
            for (Score score : scores) {
                if(score.getGuesser().getId() == userSvc.currentUser().getId() && score.getGuessee().getId() == usersRepo.findById(id).getId()) {
                    return "redirect:/randomGuess";
                }
            }
        }

        if(userSvc.isLoggedIn()){
            Score score = new Score();
            score.setGuesser(userSvc.currentUser());
            score.setGuessee(usersRepo.findById(id));
            score.setAge(guess.getAge());
            if(guess.getAge() == userSvc.getUserAge(usersRepo.findById(id))){
                score.setCorrect(true);
            }
            if(guess.getAge() != userSvc.getUserAge(usersRepo.findById(id))){
                score.setCorrect(false);
            }
            scoreRepo.save(score);
            guess.setUser(usersRepo.findById(id));
            guessRepo.save(guess);

            return "redirect:/randomGuess";
        }

        if(!userSvc.isLoggedIn()) {
            User user = usersRepo.findById(id);
            guess.setUser(usersRepo.findById(id));
            guessRepo.save(guess);
        }
            return "redirect:/randomGuess";
    }

    @GetMapping("/randomGuess")
    public String getRandomUser(){

        User user = usersRepo.findById(userSvc.getRandomUserId());

        if(userSvc.isLoggedIn() && user.getId() == userSvc.currentUser().getId()){
            return "redirect:/randomGuess";
        }

        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/about")
    public String landingPage(){
        return"/about";
    }


    @PostMapping("/editPicture")
    public String editPic(@RequestParam ("picUrl") String photo){
        userSvc.currentUser().setPhoto(photo);
        usersRepo.save(userSvc.currentUser());
        return"redirect:/welcome";
    }

}
