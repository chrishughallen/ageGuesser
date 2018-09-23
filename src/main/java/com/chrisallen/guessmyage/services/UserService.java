package com.chrisallen.guessmyage.services;


import com.chrisallen.guessmyage.models.User;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UsersRepository userRepo;
    UserDetailsLoader userDetails;

    public UserService(UsersRepository userRepo, UserDetailsLoader userDetails) {
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

    public boolean userExists(String username){
        return userRepo.findByUsername(username) != null;
    }
}
