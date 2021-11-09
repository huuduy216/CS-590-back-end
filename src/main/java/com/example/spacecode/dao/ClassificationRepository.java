package com.example.spacecode.dao;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Classification;

public interface ClassificationRepository extends CrudRepository<Classification, String>{
	@Query(value = "SELECT * FROM Classification WHERE name = :name", nativeQuery = true)
	Classification findbyname(@Param("name") String name);
	
	@Query(value = "SELECT * FROM Classification classification", nativeQuery = true)
	List<Classification> getAll();
	
	 @Modifying
	 @Query(value = "INSERT INTO Classification (classification.name) VALUES (:name)", nativeQuery = true)
	 @Transactional
	 void save(@Param("name") String name);
	}
	

