package com.odeofil.odeofil.service;

import com.odeofil.odeofil.exception.InformationExistException;
import com.odeofil.odeofil.exception.InformationNotFoundException;
import com.odeofil.odeofil.model.Library;
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

    @Autowired
    public void setLibraryRepository(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Library> getLibraries() {
        System.out.println("service calling getLibraries");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        List<Library> libraryList = libraryRepository.findByUserProfileId(userDetails.getUser().getId());
        if (libraryList.isEmpty()) {
            throw new InformationNotFoundException("no libraries found for " + userDetails.getUser().getId());
        } else {
            return libraryList;
        }
    }

    public Library createLibrary(Library libraryObject) {
        System.out.println("calling createLibrary()");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        Library library = libraryRepository.findLibraryIdAndUserId(libraryObject, userDetails.getUser().getId());
        if (library != null) {
            throw new InformationExistException("library with the id " + libraryObject.getId() + " already exists");
        } else {
            libraryObject.setId(userDetails.getUser().getId());
            return libraryRepository.save(libraryObject);
        }
    }

    public Library updateLibrary(Long libraryId, Library libraryObject) {
        System.out.println("calling updateLibrary()");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId + " not found");
        } else {
            library.setUserProfile(userDetails.getUser().getId());
            return libraryRepository.save(library);
        }
    }

    public String deleteLibrary(Long libraryId) {
        System.out.println("calling deleteLibrary");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if(library == null){
            throw new InformationNotFoundException("library with the id " + libraryId + " not found");
        } else {
            libraryRepository.deleteById(libraryId);
            return "Library with the id " + libraryId + " has been successfully deleted";
        }
    }


}
