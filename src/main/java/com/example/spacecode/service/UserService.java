package com.example.spacecode.service;

import com.example.spacecode.dao.UserDao;
import com.example.spacecode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findbyname(s);
        if (user == null) {
            throw new UsernameNotFoundException("user not exist");
        }
        return user;
    }
    
	public String addUser(User user) {
	
	try {
        userDao.save(user);
		return "User created";
	} catch(Exception e) {
		return "failed";
	}
	}
}