package com.example.spacecode.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Family;
import com.example.spacecode.model.Implementation;

public interface ImplementationRepository extends CrudRepository <Implementation, String>{
	@Query(value = "SELECT * FROM Implementation implementation WHERE implementation.name = :name", nativeQuery = true)
	Implementation findbyname(@Param("name") String name);
	
	@Query(value = "SELECT * FROM Implementation implementation", nativeQuery = true)
	List<Implementation> getAll();
	
	@Modifying
    @Query(value = "INSERT INTO Implementation (name) VALUES (:name)", nativeQuery = true)
    @Transactional
    void save(@Param("name") String name);
	
	@Modifying
    @Query(value = "INSERT INTO Implementation (name, idFamily) VALUES (:name, :idFamily)", nativeQuery = true)
    @Transactional
    void save(@Param("name") String name, @Param("idFamily") int idFamily);
}
