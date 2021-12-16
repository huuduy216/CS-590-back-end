package com.example.spacecode.dao;

import com.example.spacecode.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

import javax.transaction.Transactional;

public interface UserDao extends CrudRepository<User,String> {

    @Query(value = "SELECT * FROM RegisteredUser WHERE userName = :userName", nativeQuery = true)
    User findbyname(@Param("userName") String userName);


    @Modifying
    @Query(value = "INSERT INTO RegisteredUser (userName, password, isAdmin) VALUES (:userName, :password, FALSE )", nativeQuery = true)
    @Transactional
    void save(@Param("userName") String userName, @Param("password") String password);
    
    @Modifying
    @Query(value = "DELETE FROM RegisteredUser WHERE (userName = :userName)", nativeQuery = true)
    @Transactional
    void delete(@Param("userName") String userName);

    @Modifying
    @Query(value = "DELETE FROM History WHERE (userName = :userName)", nativeQuery = true)
    @Transactional
    void deleteHistory(@Param("userName") String userName);

}
