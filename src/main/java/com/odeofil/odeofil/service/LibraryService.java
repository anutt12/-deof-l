package com.odeofil.odeofil.service;

import com.odeofil.odeofil.exception.InformationExistException;
import com.odeofil.odeofil.exception.InformationNotFoundException;
import com.odeofil.odeofil.model.Artist;
import com.odeofil.odeofil.model.Library;
import com.odeofil.odeofil.repository.ArtistRepository;
import com.odeofil.odeofil.repository.LibraryRepository;
import com.odeofil.odeofil.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;
    private ArtistRepository artistRepository;
    // private AlbumRepository albumRepository;

    @Autowired
    public void setLibraryRepository(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

//    @Autowired
//    public void setAlbumRepository(AlbumRepository albumRepository) {this.albumRepository = albumRepository;}

    public List<Library> getLibraries(){
        System.out.println("service calling getLibraries ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Library> libraryList = libraryRepository.findByUserId(userDetails.getUser().getId());
        if (libraryList.isEmpty()) {
            throw new InformationNotFoundException("no libraries found for user id " + userDetails.getUser().getId());
        } else {
            return libraryList;
        }
    }

    public Library getLibrary(Long libraryId) {
        System.out.println("service calling getLibraries");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("no libraries found for " + userDetails.getUser().getId());
        } else {
            return library;
        }
    }

    public Library createLibrary(Library libraryObject) {
        System.out.println("calling createLibrary()");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        Library library = libraryRepository.findByUserIdAndName(userDetails.getUser().getId(), libraryObject.getName());
        if (library != null) {
            throw new InformationExistException("library with the id " + libraryObject.getId() + " already exists");
        } else {
            libraryObject.setId(userDetails.getUser().getId());
            return libraryRepository.save(libraryObject);
        }
    }

    public Library updateLibrary(Long libraryId, Library libraryObject) {
        System.out.println("service calling updateLibrary()");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId + " not found");
        } else {
            library.setUser(userDetails.getUser());
            library.setDescription(libraryObject.getDescription());
            library.setName(libraryObject.getName());
            return libraryRepository.save(library);
        }
    }

    public String deleteLibrary(Long libraryId) {
        System.out.println("service calling delete Library");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if(library == null){
            throw new InformationNotFoundException("library with the id " + libraryId + " not found");
        } else {
            libraryRepository.deleteById(libraryId);
            return "Library with the id " + libraryId + " has been successfully deleted";
        }
    }

    //*****Service for Artist Class*****//

    public Artist createLibraryArtist(Long libraryId, Artist artistObject) {
        System.out.println("service calling createLibraryArtist ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException(
                    "library with id " + libraryId + " does not belongs to this user or library does not exist");
        }
        Artist artist = artistRepository.findByUserIdAndName(userDetails.getUser().getId(), artistObject.getName());
        if (artist != null) {
            throw new InformationExistException("artist with name " + artist.getName() + " already exists");
        }
        artistObject.setUser(userDetails.getUser());
        artistObject.setLibrary(library);
        return artistRepository.save(artistObject);
    }

}
