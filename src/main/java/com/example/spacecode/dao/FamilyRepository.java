package com.example.spacecode.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Family;
import com.example.spacecode.model.Implementation;

public interface FamilyRepository extends CrudRepository <Family, String>{
	@Query(value = "SELECT * FROM Family family WHERE famiy.name = :name", nativeQuery = true)
	Family findbyname(@Param("name") String name);
	
	 @Modifying
	    @Query(value = "INSERT INTO Family (name) VALUES (:name)", nativeQuery = true)
	    @Transactional
	    void save(@Param("name") String name);
}
