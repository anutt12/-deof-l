package com.odeofil.odeofil.model;

public class UserProfile {

    private Long id;
    private String userName;
    private String profileDescription;

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
