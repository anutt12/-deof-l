package com.odeofil.odeofil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class UserProfile {

@Id
@Column
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column
    private String userName;

@Column
    private String profileDescription;

@JsonIgnore
@OneToOne(mappedBy = "userProfile")
private User user;

    public UserProfile() {
    }

    public UserProfile(Long id, String userName, String profileDescription) {
        this.id = id;
        this.userName = userName;
        this.profileDescription = profileDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                '}';
    }
}
