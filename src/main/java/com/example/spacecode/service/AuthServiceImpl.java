package com.example.spacecode.service;

import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.dao.UserDao;
import com.example.spacecode.golbal.JwtTokenUtil;
import com.example.spacecode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthServiceImpl implements AuthService {  @Autowired
private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDao userDao;

	// login
	@Override
	public JSONObject login( String username, String password ) {

		// find user by username
		final UserDetails userDetails = userDao.findbyname(username);

		JSONObject json = new JSONObject();


		if(userDetails==null){
			json.put("auth", false);
			json.put("message","user not exist");
			return json;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(!encoder.matches(password,userDetails.getPassword())){
			json.put("auth", false);
			json.put("message","password not correct");
			return json;
		}
		//find user by username
		final String token = jwtTokenUtil.generateToken(userDetails);
		Collection<? extends GrantedAuthority> role = userDetails.getAuthorities();
		json.put("auth",true);
		json.put("token", token);
		json.put("role",role);
		return json;
	}

	// register
	@Override
	public User register(User userToAdd ) {

		final String username = userToAdd.getUsername();
		if(userDao.findbyname(username)!=null){
			return null;
		}

		//encode password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String rawPassword = userToAdd.getPassword();
		String encodedPassword = encoder.encode(rawPassword);
		userToAdd.setPassword( encodedPassword);
		userDao.save(userToAdd.getUsername(),userToAdd.getPassword());
		return userDao.findbyname(username);
	}
}
