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

import com.example.spacecode.model.Family;
import com.example.spacecode.service.FamilyService;

@CrossOrigin(origins = "*")
@RestController
public class FamilyController {
	 @Autowired
	 private FamilyService familyService;

	// add
	@RequestMapping(value = "/family", method = RequestMethod.POST)
	public Family save(@RequestBody Map<String, String> map ) throws AuthenticationException {
		if(map.containsKey("name")) {
			System.out.println("Calling service");
			String name = map.get("name");
			if (familyService.get(name) != null) return null;
	        familyService.save(name);
	        return familyService.get(name);
	    }
	    return null;
	}
	
	// get
	@RequestMapping(value = "/family/{name}", method = RequestMethod.GET)
	public Family get(@PathVariable String name) throws AuthenticationException {
		return familyService.get(name);
	}
	
	// get all in Classification
	@RequestMapping(value = "/family/{classificationId}", method = RequestMethod.GET)
	public List<Family> getByClassification(@PathVariable int classificationId) throws AuthenticationException { 
		return familyService.getByClassification(classificationId);
	}
}