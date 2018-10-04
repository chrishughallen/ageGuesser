package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.Guess;
import org.springframework.data.repository.CrudRepository;

public interface GuessRepository extends CrudRepository<Guess, Long> {


}
