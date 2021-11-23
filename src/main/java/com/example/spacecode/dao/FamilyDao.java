package com.example.spacecode.dao;

import com.example.spacecode.model.Family;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FamilyDao extends CrudRepository<Family, String> {

    Family findFamilyByIdFamily(Integer idFamily);

    @Query(value = "SELECT * FROM Family WHERE Identitykey = :Identitykey", nativeQuery = true)
    Family findByIdentitykey(String Identitykey);

    @Modifying
    @Query(value = "INSERT INTO Family  (name, Identitykey,subtitle,desription ) VALUES (:name, :Identitykey,:subtitle,:desription)", nativeQuery = true)
    @Transactional
    void save(@Param("name") String name, @Param("Identitykey") String Identitykey, @Param("desription") String desription, @Param("subtitle") String subtitle);

    @Query(value = "SELECT * FROM Family Family", nativeQuery = true)
    List<Family> getAll();

    @Modifying
    @Query(value = "DELETE FROM Family WHERE idFamily =:idFamily", nativeQuery = true)
    @Transactional
    void removeByIdFamily(@Param("idFamily") Integer idFamily);

    @Modifying
    @Query(value = "UPDATE Family SET name =:name, Identitykey = :Identitykey WHERE idFamily =:idFamily", nativeQuery = true)
    @Transactional
    void updateByFamily(  @Param("name") String name,@Param("Identitykey") String Identitykey,@Param("idFamily") Integer idFamily);

    @Modifying
    @Query(value = "UPDATE Family SET subtitle =:subtitle,desription =:desription WHERE Identitykey =:Identitykey", nativeQuery = true)
    @Transactional
    void updateByFamilyContent( @Param("Identitykey") String Identitykey, @Param("desription") String desription,@Param("subtitle") String subtitle);

}
