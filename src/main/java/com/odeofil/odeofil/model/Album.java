package com.odeofil.odeofil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "userProfileId")
    @JsonIgnore
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;


}
