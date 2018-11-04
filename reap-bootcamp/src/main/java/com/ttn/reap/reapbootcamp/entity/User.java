package com.ttn.reap.reapbootcamp.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 2,message = "Should be greater than 2")
    @Column(nullable = false)
    @NotEmpty(message = "Please enter your name")
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Email(message = "Please enter a valid email address")
    @Column(nullable = false)
    @NotEmpty(message = "Please provide an email")
    private String email;

    @Transient
    private String photo;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String email, String photo, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
