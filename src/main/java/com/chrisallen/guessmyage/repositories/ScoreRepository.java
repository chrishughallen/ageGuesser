package com.chrisallen.guessmyage.repositories;

import com.chrisallen.guessmyage.models.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Long>{

//    @Query(countQuery = "SELECT correct FROM scores where guesser_id = ?1 AND correct = true")
//
//    public List<Score> findAllByCorrect(long guesser_id);
}
