package com.chrisallen.guessmyage.models;


import javax.persistence.*;

@Entity
@Table(name="profiles")
public class Profile {


    @Id
    @GeneratedValue
    private long id;

    @Column
    public int age;

    @Column
    public String pic_url;

    @OneToOne
    User user;



    public Profile(Profile copy) {
        id = copy.id;
        age = copy.age;
        pic_url = copy.pic_url;
        user = copy.user;
    }

    public Profile(int age, String pic_url, User user) {
        this.age = age;
        this.pic_url = pic_url;
        this.user = user;
    }

    public Profile() {
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

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
