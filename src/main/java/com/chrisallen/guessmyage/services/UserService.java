package com.chrisallen.guessmyage.services;


import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {
    UsersRepository userRepo;
    UserDetailsLoader userDetails;

    public UserService( UsersRepository userRepo, UserDetailsLoader userDetails) {
        this.userRepo = userRepo;
        this.userDetails = userDetails;
    }

    public boolean isLoggedIn() {
        boolean isAnonymousUser =
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return ! isAnonymousUser;
    }

    public User currentUser(){
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findById(user.getId());
    }

    public int getUserAge(User user){
        LocalDate dob = LocalDate.parse(user.getDob().toString());
        int age = Period.between(dob, LocalDate.now()).getYears();
        return age;
    }


    public boolean userExists(String username){
        return userRepo.findByUsername(username) != null;
    }


    public long getRandomUserId() {
        User user = userRepo.findUser();
        return user.getId();
    }



}
