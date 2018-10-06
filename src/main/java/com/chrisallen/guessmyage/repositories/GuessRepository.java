package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.Guess;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GuessRepository extends CrudRepository<Guess, Long> {

    List<Guess> findAllByUser_id (Long id);

}
