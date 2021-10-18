package com.odeofil.odeofil.controller;

import com.odeofil.odeofil.model.Artist;
import com.odeofil.odeofil.model.Library;
import com.odeofil.odeofil.repository.LibraryRepository;
import com.odeofil.odeofil.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class LibraryController {

    private LibraryRepository libraryRepository;

    private LibraryService libraryService;

    @GetMapping("/hello-Willie")
    public String helloWillie() {
        return "Hello Willie!";
    }

    @GetMapping("/libraries/")
    public List<Library> getAllLibraries(){
        System.out.println("calling getAllLibraries ==>");
        return libraryService.getLibraries();
    }

    @GetMapping("/libraries/{libraryId}")
    public Library getLibrary(@PathVariable Long libraryId) {
        System.out.println("calling getLibrary()");
        return libraryService.getLibrary(libraryId);
    }

    @PostMapping("/libraries/")
    public Library createLibrary(@RequestBody Library libraryObject) {
        System.out.println("calling createLibrary()");
        return libraryService.createLibrary(libraryObject);
    }
    @PutMapping("/libraries/{libraryId}")
    public Library updateLibrary(@PathVariable(value = "libraryId") Long libraryId, @RequestBody Library libraryObject) {
        System.out.println("calling updateLibrary ==>");
        return libraryService.updateLibrary(libraryId, libraryObject);
    }

    @DeleteMapping("/libraries/{libraryId}")
    public String deleteLibrary(@PathVariable(value = "libraryId") Long libraryId) {
        System.out.println("calling deleteLibrary()");
        return libraryService.deleteLibrary(libraryId);
    }

    //***** Artist Controller below*****//

    @PostMapping("/libraries/{libraryId}/artists")
    public Artist createLibraryArtist(
            @PathVariable(value = "libraryId") Long libraryId, @RequestBody Artist artistObject){
        System.out.println("Calling createCategoryRecipe()");
        return libraryService.createLibraryArtist(libraryId, artistObject);
    }

}