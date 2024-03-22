package com.example.driveBack.service;

import com.example.driveBack.dto.AuthRequest;
import com.example.driveBack.dto.JwtAuthenticationResponse;
import com.example.driveBack.dto.UserDTO;
import com.example.driveBack.model.User;

public interface AuthenticationService {
    UserDTO addNewUser(User user);

    JwtAuthenticationResponse login(AuthRequest request);
}
