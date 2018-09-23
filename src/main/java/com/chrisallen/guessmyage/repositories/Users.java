package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.User;
import org.springframework.data.repository.CrudRepository;
@org.springframework.stereotype.Repository
public interface Users extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
