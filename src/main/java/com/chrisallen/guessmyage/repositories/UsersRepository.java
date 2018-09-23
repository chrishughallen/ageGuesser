package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findById(long id);

}
