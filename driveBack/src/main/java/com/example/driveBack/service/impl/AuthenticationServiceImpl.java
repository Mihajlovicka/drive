package com.example.driveBack.service.impl;

import com.example.driveBack.dto.AuthRequest;
import com.example.driveBack.dto.JwtAuthenticationResponse;
import com.example.driveBack.dto.UserDTO;
import com.example.driveBack.model.Role;
import com.example.driveBack.model.User;
import com.example.driveBack.repo.UserRepository;
import com.example.driveBack.service.AuthenticationService;
import com.example.driveBack.service.JwtService;
import org.aspectj.weaver.EnumAnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDTO addNewUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail()).isPresent())  throw new EntityExistsException("User exists");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.PASSENGER);
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Override
    public JwtAuthenticationResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, user.getEmail());
    }
}
