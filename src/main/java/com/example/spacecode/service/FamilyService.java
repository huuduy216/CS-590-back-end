package com.example.spacecode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spacecode.dao.FamilyRepository;
import com.example.spacecode.model.Family;

@Service
public class FamilyService {
	@Autowired
	private FamilyRepository familyRepo;
	

	public Family save(String name) {
		System.out.println("Service");
		familyRepo.save(name);
		return familyRepo.findbyname(name);
	}
	
	public Family get(String name) {
		return familyRepo.findbyname(name);
	}
	
	public List<Family> get() {
		return familyRepo.getAll();
	}
	
	public List<Family> getByClassification(int classificationId) {
		return familyRepo.getByClassification(classificationId);
	}
}