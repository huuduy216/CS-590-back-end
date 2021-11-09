package com.example.spacecode.service;

import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.model.User;

public interface  AuthService {

    User register( User userToAdd );
    JSONObject login(String username, String password );
}
