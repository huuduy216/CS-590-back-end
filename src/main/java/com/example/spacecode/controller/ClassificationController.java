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
import com.google.gson.Gson;

@CrossOrigin(origins = "*")
@RestController
public class ClassificationController {
	 @Autowired
	 private ClassificationService classificationService;
	 
	 private Gson gson = new Gson();
	// add
	@RequestMapping(value = "/classification", method = RequestMethod.POST)
	public Classification save(@RequestBody Map<String, String> map) throws AuthenticationException {
		if(map.containsKey("name")) {
			String name = map.get("name");
			if (classificationService.get(name) != null) return null;
	        classificationService.save(name);
	        return classificationService.get(name);
	    }
	    return null;
	}
	
	// save structure
	@RequestMapping(value = "/classification/save", method = RequestMethod.POST)
	public Classification save(@RequestBody String data ) throws AuthenticationException {
		Classification[] classifications = gson.fromJson(data, Classification[].class);
		for (Classification c : classifications) {
			if (classificationService.get(c.getName()) != null) continue;
	        classificationService.saveNestedStructure(c);
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
