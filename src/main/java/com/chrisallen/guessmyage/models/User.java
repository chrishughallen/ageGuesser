package com.chrisallen.guessmyage.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {


        @Id
        @GeneratedValue
        private long id;

        @Column (unique = true, nullable = false, length = 255)
        @NotBlank(message = "Please enter your email")
        private String email;

        @Column(unique = true, nullable = false, length = 100)
        private String username;

        @Column(nullable = false)
        @NotBlank(message = "Please enter a password")
        private String password;

        @Column(nullable = false)
        private java.sql.Date dob;

        @Column(nullable = false)
        @NotBlank(message = "Please select a photo")
        private String photo;

        public User() {

        }

        public User(String email, String password, java.sql.Date dob, String photo) {
                this.email = email;
                this.password = password;
                this.dob = dob;
                this.photo = photo;
        }

        public User(User copy) {
                id = copy.id;
                email = copy.email;
                username = copy.email;
                password = copy.password;
                dob = copy.dob;
                photo = copy.photo;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() { return email; }

        public void setEmail(String email) { this.email = email; }

        public java.sql.Date getDob() { return dob; }

        public void setDob(java.sql.Date dob) { this.dob = dob; }

        public String getPhoto() { return photo; }

        public void setPhoto(String photo) { this.photo = photo; }
}
