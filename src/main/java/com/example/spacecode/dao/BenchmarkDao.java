package com.example.spacecode.dao;

import com.example.spacecode.model.Benchmark;
import com.example.spacecode.model.Implementation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;

public interface BenchmarkDao  extends CrudRepository<Benchmark, String> {

    @Query(value = "SELECT * FROM Benchmark WHERE id_Algorithm=:id_Algorithm", nativeQuery = true)
    ArrayList<Benchmark> findBenchmarksByFk(Integer id_Algorithm);

    @Modifying
    @Query(value = "INSERT INTO Benchmark  (username,date,cpu,ram,gpu,L1,L2,L3,time,space,likes,stars,id_Algorithm) VALUES (:username,:date,:cpu,:ram,:gpu,:L1,:L2,:L3,:time,:space,:likes,:stars,:id_Algorithm)", nativeQuery = true)
    @Transactional
    void save(@Param("username") String username,@Param("date") String date,@Param("cpu") String cpu,@Param("ram") String ram,@Param("gpu") String gpu,@Param("L1") String L1,@Param("L2") String L2,@Param("L3") String L3,@Param("time") String time,@Param("space") String space,@Param("likes") Integer likes,@Param("stars") Integer stars,@Param("id_Algorithm") Integer id_Algorithm);

}
