package com.example.spacecode.dao;

import com.example.spacecode.model.Classification;
import com.example.spacecode.model.Implementation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ClassificationDao extends CrudRepository<Classification, String> {

//    @Query(value = "SELECT * FROM Classification WHERE Identitykey = :Identitykey", nativeQuery = true)
    @Query(value = "SELECT * FROM Classification WHERE Identitykey = :Identitykey", nativeQuery = true)
    Classification findByIdentitykey(String Identitykey);

    @Modifying
    @Query(value = "INSERT INTO Classification  (name, Identitykey,desription ) VALUES (:name, :Identitykey,:desription)", nativeQuery = true)
    @Transactional
    void save(@Param("name") String name, @Param("Identitykey") String Identitykey, @Param("desription") String desription);

    @Query(value = "SELECT * FROM Classification classification", nativeQuery = true)
    List<Classification> getAll();

    @Modifying
    @Query(value = "DELETE FROM Classification WHERE idClassificaiton =:idClassificaiton", nativeQuery = true)
    @Transactional
    void removeByIdClassification(@Param("idClassificaiton") Integer idClassificaiton);

    @Modifying
    @Query(value = "UPDATE Classification SET name =:name,desription =:desription WHERE Identitykey =:Identitykey", nativeQuery = true)
    @Transactional
    void updateByClassification( @Param("Identitykey") String Identitykey,@Param("name") String name, @Param("desription") String desription);
}
