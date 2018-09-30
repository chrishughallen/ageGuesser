package com.chrisallen.guessmyage.services;


import com.chrisallen.guessmyage.repositories.ProfileRepository;
import com.chrisallen.guessmyage.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    UsersRepository userRepo;
    UserDetailsLoader userDetails;
    UserService userSvc;
    ProfileRepository profileRepo;

    public ProfileService(UserService userSvc, ProfileRepository profileRepo, UsersRepository userRepo, UserDetailsLoader userDetails) {
        this.userRepo = userRepo;
        this.userSvc = userSvc;
        this.userDetails = userDetails;
        this.profileRepo = profileRepo;
    }

        public boolean hasProfile(){
        return profileRepo.findByUser(userSvc.currentUser()) != null;
    }

}
