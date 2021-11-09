package com.example.spacecode.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Implementation;
import com.example.spacecode.model.Problem;

public interface ProblemRepository extends CrudRepository <Problem, String>{
	@Query(value = "SELECT * FROM Problem problem WHERE problem.idProblem = :idProblem", nativeQuery = true)
	Problem findbyid(@Param("idProblem") String idProblem);
//	@Query(value = "SELECT * FROM Problem problem WHERE problem.code = :code", nativeQuery = true)
//	Problem findbycode(@Param("code") String code);
//	@Query(value = "SELECT * FROM Problem problem WHERE problem.idProblem = :idProblem", nativeQuery = true)
//	Problem findbytestcase(@Param("testcase") String testcase);
	
	 @Modifying
	    @Query(value = "INSERT INTO Problem (code, testcase) VALUES (:code, :testcase)", nativeQuery = true)
	    @Transactional
	    void save(@Param("code") String code, @Param("testcase") String testcase);
}
