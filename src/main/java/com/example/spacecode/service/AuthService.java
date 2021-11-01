package com.example.spacecode.service;

import com.example.spacecode.model.User;

public interface  AuthService {

    User register( User userToAdd );
    String login( String username, String password );
}
