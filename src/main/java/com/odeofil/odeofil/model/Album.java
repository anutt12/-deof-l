package com.odeofil.odeofil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(name = "libraryId")
    private Library library;

    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;

    @Column
    private String description;

    @Column
    private String label;

    @Column
    private Date releaseDate;

}
