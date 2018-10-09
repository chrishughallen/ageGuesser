package com.chrisallen.guessmyage.services;


import com.chrisallen.guessmyage.models.Guess;
import com.chrisallen.guessmyage.models.Score;
import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.ScoreRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessService {

    private GuessRepository guessRepo;
    private UsersRepository userRepo;
    private UserService userSvc;
    private ScoreRepository scoreRepo;

    public GuessService(GuessRepository guessRepo, UsersRepository userRepo, UserService userSvc, ScoreRepository scoreRepo) {
        this.guessRepo = guessRepo;
        this.userRepo = userRepo;
        this.userSvc = userSvc;
        this.scoreRepo = scoreRepo;
    }


    public List<Score> getUserTotalGuesses(User user){
        return scoreRepo.findAllByguesser_id(user.getId());
    }



    public int getUserCorrectGuesses(List<Score> guesses){
        int correctGuesses = 0;
        for (Score guess : guesses) {
            if(guess.isCorrect()){
                correctGuesses++;
            }
        }
        return correctGuesses;
    }


    public int totalGuessesOfUsersAge(User user){
        List<Guess>otherGuesses = guessRepo.findAllByUser_id(user.getId());
        return otherGuesses.size();
    }

    public int findUsersAverageAge(User user){
        List<Guess>guessesOfUsersAge = guessRepo.findAllByUser_id(user.getId());
        int sumOfAllAgeGuesses = 0;
        for (Guess guess : guessesOfUsersAge) {
            sumOfAllAgeGuesses += guess.getAge();
        }
        return sumOfAllAgeGuesses/guessesOfUsersAge.size();

    }

    public boolean guessIsCorrect(Guess guess, long id, Score score){
        if(guess.getAge() == userSvc.getUserAge(userRepo.findById(id))){
            return true;
        }
        else return false;
    }
}
