package com.odeofil.odeofil.controller;

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
    public String helloWillie(){
        return "Hello Willie!";
    }

    @GetMapping("/libraries/{libraryId}")
    public Library getLibrary(@PathVariable Long libraryId) {
        System.out.println("calling getLibrary()");
        return libraryService.getLibrary(libraryId);
    }

        @PostMapping("/libraries/")
        public Library createLibrary(@RequestBody Library libraryObject){
            System.out.println("calling createLibrary()");
            return libraryService.createLibrary(libraryObject);
        }


    }