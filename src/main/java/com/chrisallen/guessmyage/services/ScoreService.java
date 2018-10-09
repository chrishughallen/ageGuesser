package com.chrisallen.guessmyage.services;

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




}
