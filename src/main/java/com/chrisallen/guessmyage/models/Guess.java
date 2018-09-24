package com.chrisallen.guessmyage.models;


import javax.persistence.*;

@Entity
@Table(name="guesses")
public class Guess {


    @Id
    @GeneratedValue
    private long id;

    @Column
    private int age;

    @OneToOne
    User user;

    public Guess() {
    }

    public Guess(int age, User user) {
        this.age = age;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
