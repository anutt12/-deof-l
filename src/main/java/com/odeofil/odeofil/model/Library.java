package com.odeofil.odeofil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "libraries")
public class Library {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "library", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Artist> artistList;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Library(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public Library(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Artist> getArtistList(){
        return artistList;
    }

    public void setArtistList(List<Artist> artistList){
        this.artistList = artistList;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public List<Album> getAlbumList(){return albumList;}

    public void  setAlbumList(List<Album> albumList) {this.albumList = albumList;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
