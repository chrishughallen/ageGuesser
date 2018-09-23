package com.chrisallen.guessmyage.models;

import javax.persistence.*;


@Entity
@Table(name="scores")
public class Score {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private int correct;

    @Column
    private int incorrect;

    @Column
    private int correct_guess;

    @Column
    private int incorrect_guess;

    @OneToOne
    private User user;

    public Score(int correct, int incorrect, int correct_guess, int incorrect_guess, User user) {
        this.correct = correct;
        this.incorrect = incorrect;
        this.correct_guess = correct_guess;
        this.incorrect_guess = incorrect_guess;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public int getCorrect_guess() {
        return correct_guess;
    }

    public void setCorrect_guess(int correct_guess) {
        this.correct_guess = correct_guess;
    }

    public int getIncorrect_guess() {
        return incorrect_guess;
    }

    public void setIncorrect_guess(int incorrect_guess) {
        this.incorrect_guess = incorrect_guess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
