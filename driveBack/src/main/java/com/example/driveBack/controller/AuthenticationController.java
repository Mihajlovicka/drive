package com.example.driveBack.controller;

import com.example.driveBack.dto.AuthRequest;
import com.example.driveBack.model.User;
import com.example.driveBack.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("register")
    public ResponseEntity addNewUser(@RequestBody User userInfo) {
        return new ResponseEntity(service.addNewUser(userInfo), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthRequest request) {
        return new ResponseEntity(service.login(request), HttpStatus.OK);
    }


}
