package com.example.spacecode.dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.example.spacecode.model.History;

public interface HistoryDao  extends CrudRepository<History,String> {
    
    @Query(value = "SELECT * FROM History", nativeQuery = true)
    ArrayList<History> getHistory();
    
    @Modifying
    @Query(value = "INSERT INTO history (username, history.history) VALUES (:userName, :history) ON DUPLICATE KEY UPDATE history.history = :history", nativeQuery = true)
    @Transactional
    void save(@Param("userName") String userName, @Param("history") String history);
}
