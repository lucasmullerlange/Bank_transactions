package com.example.projetoteste.controller;


import com.example.projetoteste.domain.user.User;
import com.example.projetoteste.domain.user.UserDTO;
import com.example.projetoteste.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
            User newUser = service.createUser(user);

            return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        var Users = this.service.getAllUsers();
            return  new ResponseEntity<>(Users, HttpStatus.OK);
    }
}
