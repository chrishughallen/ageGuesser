package com.chrisallen.guessmyage.services;

import com.chrisallen.guessmyage.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    ProfilesRepository profileRepo;
    UserService userSvc;

    public ProfileService(ProfilesRepository profileRepo, UserService userSvc) {
        this.profileRepo = profileRepo;
        this.userSvc = userSvc;
    }

    public boolean userHasProfile(){
        return profileRepo.findByUser(userSvc.currentUser()) != null;
    }


}
