package com.example.spacecode.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Classification;

public interface ClassificationRepository extends CrudRepository<Classification, String>{
	@Query(value = "SELECT classification FROM Classification classification WHERE classification.name = :name", nativeQuery = true)
	Classification findbyname(@Param("name") String name);
}
