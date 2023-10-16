package com.utm.messenger.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;



    @NaturalId(mutable = true)
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
//    private String role;
    @Column(name = "activated")
    private boolean isEnabled = false;

    public User(String first_name, String last_name, String username,String email, String password, boolean isEnabled){
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.email = email;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}