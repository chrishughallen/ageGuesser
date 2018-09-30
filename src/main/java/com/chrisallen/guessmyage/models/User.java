package com.chrisallen.guessmyage.models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {


        @Id
        @GeneratedValue
        private long id;

        @Column (unique = true, nullable = false, length = 255)
        private String email;

        @Column(unique = true, nullable = false, length = 100)
        private String username;

        @Column(nullable = false)
        private String password;

        public User() {

        }

        public User(String email, String username, String password) {
                this.email = email;
                this.username = username;
                this.password = password;
        }

        public User(User copy) {
                id = copy.id;
                email = copy.email;
                username = copy.username;
                password = copy.password;
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
}
