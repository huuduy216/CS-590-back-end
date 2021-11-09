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

import com.example.spacecode.model.Classification;
import com.example.spacecode.service.ClassificationService;

@CrossOrigin(origins = "*")
@RestController
public class CodeInsertionController {
	 @Autowired
	 private ClassificationService classificationService;

	// add
	@RequestMapping(value = "/classification", method = RequestMethod.POST)
	public Classification save(@RequestBody Map<String, String> map ) throws AuthenticationException {
		System.out.println("Hello");
		if(map.containsKey("name")) {
			System.out.println("Calling service");
			String name = map.get("name");
			if (classificationService.get(name) != null) return null;
	        classificationService.save(name);
	        return classificationService.get(name);
	    }
	    return null;
	}
	
	// get
	@RequestMapping(value = "/classification/{name}", method = RequestMethod.GET)
	public Classification get(@PathVariable String name ) throws AuthenticationException {
		System.out.println("Fetching: " + name);
		System.out.println(classificationService.get(name));
		return classificationService.get(name);
	}
	
	// get
	@RequestMapping(value = "/classification/getAll", method = RequestMethod.GET)
	public List<Classification> get() throws AuthenticationException { 
		return classificationService.get();
	}
}
