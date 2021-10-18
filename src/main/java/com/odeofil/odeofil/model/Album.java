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
    private User user;

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

    public Album() {
    }

    public Album(Long id, String name, boolean isPublic, String description, String label, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.isPublic = isPublic;
        this.description = description;
        this.label = label;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPublic=" + isPublic +
                ", description='" + description + '\'' +
                ", label='" + label + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
