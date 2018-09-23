package com.chrisallen.guessmyage.models;

public class Guess {

    private int guess;

    public Guess(int guess) {
        this.guess = guess;
    }

    public Guess() {
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }
}
