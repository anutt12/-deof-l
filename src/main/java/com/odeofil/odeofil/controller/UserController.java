package com.odeofil.odeofil.controller;

import com.odeofil.odeofil.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){this.userService = userService;}

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("Controller is calling create user ==>");
        return userService.createUser(userObject);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(RequestBody LoginRequest loginRequest){
        System.out.println("Controller is callling loginUser ==>");
        return userService.loginUser(loginRequest);
    }
}
