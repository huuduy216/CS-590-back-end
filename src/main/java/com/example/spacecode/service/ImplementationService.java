package com.example.spacecode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spacecode.dao.ImplementationRepository;
import com.example.spacecode.model.Implementation;

@Service
public class ImplementationService {
	@Autowired
	private ImplementationRepository implementationRepo;
	
	public Implementation save(String name, String testcase) {
		System.out.println("Service");
		implementationRepo.save(name, testcase);
		return implementationRepo.findbyname(name);
	}
	
	public Implementation get(String name) {
		return implementationRepo.findbyname(name);
	}
	
	public List<Implementation> get() {
		return implementationRepo.getAll();
	}
}