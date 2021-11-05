package com.example.spacecode.dao;

import com.example.spacecode.model.Role;
import com.example.spacecode.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//
//@Component
//public class UserRepository {
//
//    private static List<User> users = new ArrayList<>();
//    private static List<Role> roles = new ArrayList<>();
//
//    static {
//        roles.add(new Role(Long.valueOf("1"), "ROLE_ADMIN"));
//        //roles.add(new Role(Long.valueOf("2"), "ROLE_ADMIN"));
//        users.add(new User(Long.valueOf("11621"), "admin", "$2a$10$V52cdzGAHx9nX6TJTSfsC.K0uSKBWlIV6SqFi94ExtSTWhJl4noRa", roles));
//    }
//
//    public User findByUsername(String s) {
//    	
//        for(User user : users){
//            if(user.getUsername().equals(s)){
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public User save(User userToAdd) {
//        if(findByUsername(userToAdd.getUsername()) == null){
//            users.add(userToAdd);
//            return userToAdd;
//        }
//        return null;
//    }
//}

public interface UserRepository extends CrudRepository <User, String>{
	@Query(value = "SELECT * FROM RegisteredUser WHERE userName = :userName", nativeQuery = true)
	User findbyname(@Param("userName") String userName);
	
    @Modifying
	@Query(value = "INSERT INTO RegisteredUser (userName, password, isAdmin) VALUES (:userName, :password, FALSE)", nativeQuery = true)
    @Transactional
	void save(@Param("userName") String userName, @Param("password") String password);
}

