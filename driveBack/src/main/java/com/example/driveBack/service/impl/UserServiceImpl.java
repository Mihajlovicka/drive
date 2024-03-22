package com.example.driveBack.service.impl;

import com.example.driveBack.model.Driver;
import com.example.driveBack.model.User;
import com.example.driveBack.model.Vehicle;
import com.example.driveBack.model.VehicleState;
import com.example.driveBack.repo.UserRepository;
import com.example.driveBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findUserByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}

	@Override
	public User getLoggedIn() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
