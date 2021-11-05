package com.example.spacecode.dao;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Implementation;

public interface ImplementationRepository extends CrudRepository <Implementation, String>{
	@Query(value = "SELECT classification FROM Classification classification WHERE classification.name = :name", nativeQuery = true)
	Implementation findbyname(@Param("name") String implementation);
}
