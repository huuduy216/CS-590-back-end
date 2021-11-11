package com.example.spacecode.dao;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Benchmark;
import com.example.spacecode.model.Classification;
import com.example.spacecode.model.User;

public interface BenchmarkRepository extends CrudRepository<Benchmark,String> {

    @Query(value = "SELECT * FROM Benchmark benchmark WHERE benchMark.idBenchMark = :idBenchmark", nativeQuery = true)
    Benchmark findbyid(@Param("idBenchmark") String idBenchmark);
//    @Query(value = "SELECT * FROM Benchmark benchmark WHERE benchMark.machineConfig = :machineConfig", nativeQuery = true)
//    Benchmark findbymachineConfig(@Param("machineConfig") String machineConfig);
//    @Query(value = "SELECT * FROM Benchmark benchmark WHERE benchMark.score = :score", nativeQuery = true)
//    Benchmark findbyscore(@Param("Score") String score);
//    @Query(value = "SELECT * FROM Benchmark benchmark WHERE benchMark.date = :date", nativeQuery = true)
//    Benchmark findbydate(@Param("Date") String date);
    
    @Query(value = "SELECT * FROM Benchmark benchmark", nativeQuery = true)
	List<Benchmark> getAll();
    
    @Query(value = "SELECT * FROM Benchmark benchmark WHERE benchmark.idProblem = :idProblem", nativeQuery = true)
	List<Benchmark> getAllByProblemId(@Param("idProblem") int problemId);

    @Modifying
    @Query(value = "INSERT INTO Benchmark (machineConfig, runTime, date) VALUES (:machineConfig, :runTime, :date )", nativeQuery = true)
    @Transactional
    void save(@Param("machineConfig") String machineConfig, @Param("runTime") String runTime, @Param("date") String date);
    
    @Modifying
    @Query(value = "INSERT INTO Benchmark (machineConfig, runTime, date, idRegisteredUser, idProblem) VALUES (:machineConfig, :runTime, :date, idRegisteredUser, idProblem )", nativeQuery = true)
    @Transactional
    void save(@Param("machineConfig") String machineConfig, @Param("runTime") double runTime, @Param("date") Date date, @Param("idRegisteredUser") int idRegisteredUser, @Param("idProblem") int problemId);



	



	
}