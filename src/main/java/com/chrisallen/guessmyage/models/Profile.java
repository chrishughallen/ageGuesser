package com.chrisallen.guessmyage.models;


import javax.persistence.*;

@Entity
@Table(name="profiles")
public class Profile {

    @Id
    @GeneratedValue
    private long id;

    @Column (length = 255, nullable = false)
    private int age;

    @Column(length = 255)
    private String picture;

    @OneToOne
    private User user;

    public Profile() {
    }

    public Profile(int age, String picture, User user) {
        this.age = age;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
