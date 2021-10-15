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

    @OneToMany(mappedBy = "library", orhpanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Artist> artistList;

    @OneToMany(mappedBy = "library"), orphanRemoval = true)
    @LazyCollection(LazyCollection.FALSE)
    private List<Album> albumList;

    @OneToOne
    @JoinColumn(name = "user_profile_id")
    @JsonIgnore
    private UserProfile userProfile;

    public Library(){

    }

    public Library(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                '}';
    }

    public List<Artist> getArtistList(){
        return artistList;
    }

    public void setArtistList(List<Artist> artistList){
        this.artistList = artistList;
    }

    public UserProfile getUserProfile(){
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile){
        this.userProfile = userProfile;
    }

    public List<Album> getAlbumList(){return albumList;}

    public void  setAlbumList(List<Album> albumList) {this.albumList = albumList;}

}
