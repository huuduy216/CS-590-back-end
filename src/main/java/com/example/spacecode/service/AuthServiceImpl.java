package com.example.spacecode.service;

import com.example.spacecode.CustromBCryptEncoder;
import com.example.spacecode.dao.UserRepository;
import com.example.spacecode.golbal.JwtTokenUtil;
import com.example.spacecode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	// login
	@Override
	public String login(String username, String password) {

		// find user by username
		System.out.println("Username to query: " + username);
		final UserDetails userDetails = userRepository.findbyname(username);
		BCryptPasswordEncoder encoder = new CustromBCryptEncoder();
		System.out.println("stored password: " + userDetails.getPassword() + " encoded? " + encoder.matches(password, userDetails.getPassword()));
		if (!encoder.matches(password, userDetails.getPassword())) {
			System.out.println("returning null");
			return null;
		}
		
		System.out.println("Authenticated!");
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		System.out.println("UpToken generated");
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("UpToken authenticated");
		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println("Token generated");
		System.out.println("Token: " + token);
		return token;
	}

	// register
	@Override
	public User register(User userToAdd) {

		final String username = userToAdd.getUserName();
		if (userRepository.findbyname(username) != null) {
			UserDetails details = userRepository.findbyname(username);
			System.out.println("UserDetails: " + details);
			return null;
		}

		// encode password
		BCryptPasswordEncoder encoder = new CustromBCryptEncoder();
		final String rawPassword = userToAdd.getPassword();
		userToAdd.setPassword(encoder.encode(rawPassword));
		userRepository.save(username, userToAdd.getPassword());
		return userRepository.findbyname(username);
	}
}
