package com.example.spacecode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spacecode.dao.BenchmarkRepository;
import com.example.spacecode.model.Benchmark;

@Service
public class BenchmarkService {
	@Autowired
	private BenchmarkRepository benchmarkRepo;
	
	public Benchmark save(String idBenchMark,String machineConfig, String score, String date) {
		System.out.println("Service");
		benchmarkRepo.save(machineConfig, score, date);
		return benchmarkRepo.findbyid(idBenchMark);
	}
	
	public Benchmark get(String idBenchmark) {
		return benchmarkRepo.findbyid(idBenchmark);
	}
	
	public List<Benchmark> get() {
		return benchmarkRepo.getAll();
	}
}