package com.example.spacecode.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spacecode.model.Benchmark;
import com.example.spacecode.service.BenchmarkService;

@CrossOrigin(origins = "*")
@RestController
public class BenchmarkController {
	 @Autowired
	 private BenchmarkService benchmarkService;

	// add
	@RequestMapping(value = "/benchmark", method = RequestMethod.POST)
	public Benchmark save(@RequestBody Map<String, String> map ) throws AuthenticationException {
		System.out.println("Hello");
		if(map.containsKey("idBenchmark")) {
			System.out.println("Calling service");
			String idBenchmark = map.get("idBenchmark");
			String machineConfig = map.get("machineConfig");
			String score = map.get("Score");
			String date = map.get("date");
			if (benchmarkService.get(idBenchmark) != null) return null;
	        benchmarkService.save(idBenchmark, machineConfig, score, date);
	        
	        return benchmarkService.get(idBenchmark);
	    }
	    return null;
	}
	
	// get
	@RequestMapping(value = "/benchmark/{idBenchmark}", method = RequestMethod.GET)
	public Benchmark get(@PathVariable String idBenchmark, String machineConfig, String score, String date ) throws AuthenticationException {
		System.out.println("Fetching: " + idBenchmark + machineConfig + score + date);
		System.out.println(benchmarkService.get(idBenchmark));
		return benchmarkService.get(idBenchmark);
	}
	
	// get
	@RequestMapping(value = "/benchmark/getAll", method = RequestMethod.GET)
	public List<Benchmark> get() throws AuthenticationException { 
		return benchmarkService.get();
	}
}
