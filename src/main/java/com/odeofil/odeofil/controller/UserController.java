package com.odeofil.odeofil.controller;

import com.odeofil.odeofil.model.User;
import com.odeofil.odeofil.model.response.LoginRequest;
import com.odeofil.odeofil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
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
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("Controller is calling loginUser ==>");
        return userService.loginUser(loginRequest);
    }
}
