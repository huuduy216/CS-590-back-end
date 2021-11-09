package com.example.spacecode.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spacecode.model.Benchmark;
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
    


    @Modifying
    @Query(value = "INSERT INTO Benchmark (machineConfig, score, date) VALUES (:machineConfig, :score, :date )", nativeQuery = true)
    @Transactional
    void save(@Param("machineConfig") String machineConfig, @Param("score") String score, @Param("date") String date);
}