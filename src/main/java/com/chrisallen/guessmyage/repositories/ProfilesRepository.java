package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.Profile;
import com.chrisallen.guessmyage.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends CrudRepository<Profile, Long> {
    Profile findById(long id);
    Profile findByUser(User user);

}
