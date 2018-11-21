package com.ttn.reap.reapbootcamp.entity;


import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 2, message = "Name should be greater than 2")
    @Column(nullable = false)
    @NotEmpty(message = "Please enter your name")
    private String fname;

    @Column(nullable = true)
    @Size(min = 2, message = "It should be greater than 2")
    private String lname;

    @Size(min = 2, max = 35, message = "Please enter a valid email address")
    @Email(message = "Please enter a valid email address")
    @Column(nullable = false)
    @NotEmpty(message = "Please provide an email")
    private String email;

    @Transient
    private String photo;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 2, max = 8,message = "Length of password must be between 2-8")
    private String password;

    private Integer goldCount;
    private Integer silverCount;
    private Integer bronzeCount;


    private Integer earnedGold;
    private Integer earnedSilver;
    private Integer earnedBronze;

    private Integer roleId;


    public User() {
    }

    public User(@Size(min = 2, message = "Should be greater than 2") @NotEmpty(message = "Please enter your name") String fname, String lname, @Email(message = "Please enter a valid email address") @NotEmpty(message = "Please provide an email") String email, String photo, @NotEmpty(message = "Password cannot be empty") String password, Integer goldCount, Integer silverCount, Integer bronzeCount, Integer roleId) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.photo = photo;
        this.password = password;
        this.goldCount = goldCount;
        this.silverCount = silverCount;
        this.bronzeCount = bronzeCount;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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

    public Integer getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(Integer goldCount) {
        this.goldCount = goldCount;
    }

    public Integer getSilverCount() {
        return silverCount;
    }

    public void setSilverCount(Integer silverCount) {
        this.silverCount = silverCount;
    }

    public Integer getBronzeCount() {
        return bronzeCount;
    }

    public void setBronzeCount(Integer bronzeCount) {
        this.bronzeCount = bronzeCount;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public Integer getEarnedGold() {
        return earnedGold;
    }

    public void setEarnedGold(Integer earnedGold) {
        this.earnedGold = earnedGold;
    }

    public Integer getEarnedSilver() {
        return earnedSilver;
    }

    public void setEarnedSilver(Integer earnedSilver) {
        this.earnedSilver = earnedSilver;
    }

    public Integer getEarnedBronze() {
        return earnedBronze;
    }

    public void setEarnedBronze(Integer earnedBronze) {
        this.earnedBronze = earnedBronze;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", password='" + password + '\'' +
                ", goldCount=" + goldCount +
                ", silverCount=" + silverCount +
                ", bronzeCount=" + bronzeCount +
                ", roleId=" + roleId +
                '}';
    }


   /* @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name="id")
            ,inverseJoinColumns = @JoinColumn(name = "id"))
    List<UserRecogonize> userRecogonizeList;*/
}