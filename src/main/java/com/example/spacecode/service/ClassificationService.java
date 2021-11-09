package com.example.spacecode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spacecode.dao.ClassificationRepository;
import com.example.spacecode.model.Classification;

@Service
public class ClassificationService {
	@Autowired
	private ClassificationRepository classificationRepo;
	
	public Classification save(String name) {
		System.out.println("Service");
		classificationRepo.save(name);
		return classificationRepo.findbyname(name);
	}
	
	public Classification get(String name) {
		return classificationRepo.findbyname(name);
	}
	
	public List<Classification> get() {
		return classificationRepo.getAll();
	}
}