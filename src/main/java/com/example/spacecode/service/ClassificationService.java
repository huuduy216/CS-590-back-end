package com.example.spacecode.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spacecode.dao.BenchmarkRepository;
import com.example.spacecode.dao.ClassificationRepository;
import com.example.spacecode.dao.FamilyRepository;
import com.example.spacecode.dao.ImplementationRepository;
import com.example.spacecode.dao.ProblemRepository;
import com.example.spacecode.model.Benchmark;
import com.example.spacecode.model.Classification;
import com.example.spacecode.model.Family;
import com.example.spacecode.model.Implementation;
import com.example.spacecode.model.Problem;

@Service
public class ClassificationService {
	@Autowired
	private ClassificationRepository classificationRepo;
	
	@Autowired
	private FamilyRepository familyRepo;
	
	@Autowired
	private ImplementationRepository implementationRepo;
	
	@Autowired
	private ProblemRepository problemRepo;
	
	@Autowired
	private BenchmarkRepository benchmarkRepo;
	
	public Classification save(String name) {
		System.out.println("Service");
		classificationRepo.save(name);
		return classificationRepo.findbyname(name);
	}
	
	public Classification saveNestedStructure(Classification c) {
		System.out.println("Service");
		Classification classificationDB = save(c.getName());
		classificationDB.setFamily(c.getFamily());
		for (Family f : c.getFamily()) {
			familyRepo.save(f.getName(), classificationDB.getIdClassification());
			Family familyDB = familyRepo.findbyname(f.getName());
			familyDB.setImplementation(f.getImplementation());
			familyDB.setClassification(classificationDB);
			for (Implementation i : f.getImplementation()) {
				implementationRepo.save(i.getName(), f.getIdfamily());
				Implementation implementationDB = implementationRepo.findbyname(i.getName());
				implementationDB.setProblem(i.getProblem());
				for (Problem p : i.getProblem()) {
					problemRepo.save(p.getCode(), p.getTestcase(), p.getIdImplementation());
					Problem problemDB = problemRepo.findByCode(p.getCode());
					problemDB.setBenchmark(p.getBenchmark());
					for (Benchmark b : p.getBenchmark()) {
						benchmarkRepo.save(b.getMachineConfig(), b.getRunTime(), b.getDate(), b.getUserId(), problemDB.getIdProblem());
						List<Benchmark> benchmarks = benchmarkRepo.getAllByProblemId(problemDB.getIdProblem());
						problemDB.setBenchmark(new HashSet<Benchmark>(benchmarks));
					}
				}
			}
		}
		return classificationDB;
		
	}
	
	public Classification get(String name) {
		return classificationRepo.findbyname(name);
	}
	
	public List<Classification> get() {
		return classificationRepo.getAll();
	}
}