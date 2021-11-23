package com.example.spacecode.dao;

import com.example.spacecode.model.Algorithm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AlgorithmDao extends CrudRepository<Algorithm, String> {

    Algorithm findAlgorithmByIdAlgorithm(Integer idAlgorithm);

    @Query(value = "SELECT * FROM Algorithm WHERE Identitykey = :Identitykey", nativeQuery = true)
    Algorithm findByIdentitykey(String Identitykey);

    @Modifying
    @Query(value = "INSERT INTO Algorithm  (name, Identitykey,subtitle,desription ) VALUES (:name, :Identitykey,:subtitle,:desription)", nativeQuery = true)
    @Transactional
    void save(@Param("name") String name, @Param("Identitykey") String Identitykey, @Param("desription") String desription, @Param("subtitle") String subtitle);

    @Query(value = "SELECT * FROM Algorithm Algorithm", nativeQuery = true)
    List<Algorithm> getAll();

    @Modifying
    @Query(value = "DELETE FROM Algorithm WHERE idAlgorithm =:idAlgorithm", nativeQuery = true)
    @Transactional
    void removeByIdAlgorithm(@Param("idAlgorithm") Integer idAlgorithm);

    @Modifying
    @Query(value = "UPDATE Algorithm SET name =:name,Identitykey =:Identitykey WHERE idAlgorithm =:idAlgorithm", nativeQuery = true)
    @Transactional
    void updateByAlgorithm( @Param("name") String name,@Param("Identitykey") String Identitykey,@Param("idAlgorithm") Integer idAlgorithm);

    @Modifying
    @Query(value = "UPDATE Algorithm SET subtitle =:subtitle,desription =:desription WHERE Identitykey =:Identitykey", nativeQuery = true)
    @Transactional
    void updateByAlgorithmContent( @Param("Identitykey") String Identitykey, @Param("desription") String desription,@Param("subtitle") String subtitle);

}

