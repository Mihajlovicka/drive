package com.example.driveBack.service;

import com.example.driveBack.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User getLoggedIn();
}
