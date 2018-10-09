package com.chrisallen.guessmyage.services;

import com.chrisallen.guessmyage.models.Guess;
import com.chrisallen.guessmyage.models.Score;
import com.chrisallen.guessmyage.repositories.GuessRepository;
import com.chrisallen.guessmyage.repositories.ScoreRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    private GuessRepository guessRepo;
    private UsersRepository userRepo;
    private UserService userSvc;
    private ScoreRepository scoreRepo;

    public ScoreService(GuessRepository guessRepo, UsersRepository userRepo, UserService userSvc, ScoreRepository scoreRepo) {
        this.guessRepo = guessRepo;
        this.userRepo = userRepo;
        this.userSvc = userSvc;
        this.scoreRepo = scoreRepo;
    }

    public Score setupNewScore(long id, Guess guess){
        Score score = new Score();
        score.setGuesser(userSvc.currentUser());
        score.setGuessee(userRepo.findById(id));
        score.setAge(guess.getAge());
        return score;
    }




}
