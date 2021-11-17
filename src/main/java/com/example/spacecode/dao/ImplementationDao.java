package com.example.spacecode.dao;

import com.example.spacecode.model.Implementation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ImplementationDao extends CrudRepository<Implementation, String> {

    @Query(value = "SELECT * FROM Implementation implementation WHERE Identitykey = :Identitykey", nativeQuery = true)
    Implementation findbyIdentitykey(@Param("Identitykey") String Identitykey);

    @Modifying
    @Query(value = "INSERT INTO Implementation (title, Identitykey,language ) VALUES (:title, :Identitykey,:language)", nativeQuery = true)
    @Transactional
    void save(@Param("title") String title, @Param("Identitykey") String Identitykey, @Param("language") String language);
}
