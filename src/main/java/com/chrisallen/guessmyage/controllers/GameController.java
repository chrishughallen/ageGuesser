package com.chrisallen.guessmyage.controllers;

import com.chrisallen.guessmyage.models.Guess;
import com.chrisallen.guessmyage.models.Score;
import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.ScoreRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import com.chrisallen.guessmyage.services.GuessService;
import com.chrisallen.guessmyage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    private UsersRepository usersRepo;
    private UserService userSvc;
    private GuessService guessSvc;
    private GuessRepository guessRepo;
    private ScoreRepository scoreRepo;

    public GameController(UsersRepository usersRepo, UserService userSvc, GuessService guessSvc, GuessRepository guessRepo, ScoreRepository scoreRepo) {
        this.usersRepo = usersRepo;
        this.userSvc = userSvc;
        this.guessSvc = guessSvc;
        this.guessRepo = guessRepo;
        this.scoreRepo = scoreRepo;
    }



//    Displays a users page with input field for a guess. If user is logged in, it also sets up score model.

    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable long id, Model model){

        if(userSvc.randomUserIsLoggedInUser(id)){
            return "redirect:/welcome";
        }

        if(userSvc.isLoggedIn()){
            User user = userSvc.currentUser();
            model.addAttribute("loggedIn", true);
            model.addAttribute("totalGuesses", guessSvc.getUserTotalGuesses(user).size());
            model.addAttribute("correctGuesses", guessSvc.getUserCorrectGuesses(guessSvc.getUserTotalGuesses(user)));
            model.addAttribute("totalGuessesOfUser", guessSvc.totalGuessesOfUsersAge(user));
            if(guessSvc.totalGuessesOfUsersAge(user)>0){
                model.addAttribute("howOldUserLooks", guessSvc.findUsersAverageAge(user));
            }
        }

        model.addAttribute("user", usersRepo.findById(id));
        model.addAttribute("age", userSvc.getUsersAgeFromId(id));
        model.addAttribute("Guess", new Guess());
        return "/userPage";
    }


//  Checks if guess is correct/incorrect and stores score if user is logged in.
    @PostMapping("/guess/{id}")
    public String checkGuess(@PathVariable long id,
                             @ModelAttribute Guess guess,
                             Model model ){

        model.addAttribute("loggedIn", userSvc.isLoggedIn());

        if(userSvc.isLoggedIn()){
            Score score = new Score();
            score.setGuesser(userSvc.currentUser());
            score.setGuessee(usersRepo.findById(id));
            score.setAge(guess.getAge());
            score.setCorrect(guessSvc.guessIsCorrect(guess,id,score));
            scoreRepo.save(score);
            guess.setUser(usersRepo.findById(id));
            guessRepo.save(guess);
            return "redirect:/randomGuess";
        }

        guess.setUser(usersRepo.findById(id));
        guessRepo.save(guess);
        return "redirect:/randomGuess";
    }





//  Pulls a random user id and re-routes to that users guess page.
    @GetMapping("/randomGuess")
    public String getRandomUser(){
        User user = usersRepo.findById(userSvc.getRandomUserId());

//      Ensures logged in user won't see their own page for guessing
        if(userSvc.isLoggedIn() && user.getId() == userSvc.currentUser().getId()){
            return "redirect:/randomGuess";
        }
        return "redirect:/user/" + user.getId();
    }
}
