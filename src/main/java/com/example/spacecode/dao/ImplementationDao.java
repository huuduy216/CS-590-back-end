package com.example.spacecode.dao;

import com.example.spacecode.model.Algorithm;
import com.example.spacecode.model.Implementation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ImplementationDao extends CrudRepository<Implementation, String> {

    @Query(value = "SELECT * FROM Implementations WHERE id_Algorithm=:id_Algorithm AND languageName = :languageName", nativeQuery = true)
    Implementation findImplementationByLanguageNameAndFk(String languageName,Integer id_Algorithm);


    @Modifying
    @Query(value = "INSERT INTO Implementations  (subtitle, description,languageName,code,id_Algorithm) VALUES (:subtitle, :description,:languageName,:code,:id_Algorithm)", nativeQuery = true)
    @Transactional
    void save(@Param("subtitle") String subtitle, @Param("description") String description, @Param("languageName") String language, @Param("code") String code, @Param("id_Algorithm") Integer id_Algorithm);

    @Modifying
    @Query(value = "DELETE FROM Implementations WHERE idImplementation =:idImplementation", nativeQuery = true)
    @Transactional
    void removeByIdImplementation(@Param("idImplementation") Integer idImplementation);

    @Modifying
    @Query(value = "UPDATE Implementations SET code =:code WHERE id_Algorithm=:id_Algorithm AND languageName = :languageName", nativeQuery = true)
    @Transactional
    void updateByImplementationContent(@Param("code") String code,@Param("languageName") String languageName,@Param("id_Algorithm") Integer id_Algorithm);

    @Modifying
    @Query(value = "UPDATE Implementations SET id_Algorithm =:id_Algorithm WHERE idImplementation=:idImplementation", nativeQuery = true)
    @Transactional
    void updateImplementationFk(@Param("id_Algorithm") String id_Algorithm,@Param("idImplementation") Integer idImplementation);

}
