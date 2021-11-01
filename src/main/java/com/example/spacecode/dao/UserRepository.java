package com.example.spacecode.dao;

import com.example.spacecode.model.Role;
import com.example.spacecode.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    private static List<User> users = new ArrayList<>();
    private static List<Role> roles = new ArrayList<>();

    static {
        roles.add(new Role(Long.valueOf("1"), "ROLE_ADMIN"));
        //roles.add(new Role(Long.valueOf("2"), "ROLE_ADMIN"));
        users.add(new User(Long.valueOf("11621"), "admin", "$2a$10$V52cdzGAHx9nX6TJTSfsC.K0uSKBWlIV6SqFi94ExtSTWhJl4noRa", roles));
    }

    public User findByUsername(String s) {
        for(User user : users){
            if(user.getUsername().equals(s)){
                return user;
            }
        }
        return null;
    }

    public User save(User userToAdd) {
        if(findByUsername(userToAdd.getUsername()) == null){
            users.add(userToAdd);
            return userToAdd;
        }
        return null;
    }
}
