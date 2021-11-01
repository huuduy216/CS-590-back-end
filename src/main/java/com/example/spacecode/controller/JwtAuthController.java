package com.example.spacecode.controller;

import com.example.spacecode.model.Role;
import com.example.spacecode.model.User;
import com.example.spacecode.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    // login
    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public String createToken( String username,String password ) throws AuthenticationException {
        return authService.login( username, password );
    }

    // register
    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public User register(@RequestBody Map<String, String> map ) throws AuthenticationException {


        if(map.containsKey("username") && (map.containsKey("password"))){

            List<Role> roles = new ArrayList<>();
            roles.add(new Role(Long.valueOf("1"), "ROLE_NORMAL"));
            User addedUser = new User(Long.valueOf("16"),map.get("username"),map.get("password"),roles);
            return authService.register(addedUser);
        }
        return null;

    }
}
