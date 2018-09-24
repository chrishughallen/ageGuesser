package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.Guess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuessRepository extends CrudRepository<Guess, Long> {

}
