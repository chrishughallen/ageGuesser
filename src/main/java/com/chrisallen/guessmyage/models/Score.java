package com.chrisallen.guessmyage.models;


import javax.persistence.*;

@Entity
@Table(name="scores")
public class Score {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private int age;

    @Column
    private boolean correct;

    @OneToOne
    private User guesser;

    @OneToOne
    private User guessee;

    public Score(int age, boolean correct, User guesser, User guessee) {
        this.age = age;
        this.correct = correct;
        this.guesser = guesser;
        this.guessee = guessee;
    }

    public Score() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public User getGuesser() {
        return guesser;
    }

    public void setGuesser(User guesser) {
        this.guesser = guesser;
    }

    public User getGuessee() {
        return guessee;
    }

    public void setGuessee(User guessee) {
        this.guessee = guessee;
    }
}
