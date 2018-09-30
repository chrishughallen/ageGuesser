package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.Profile;
import com.chrisallen.guessmyage.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Profile findByUser(User user);
}
