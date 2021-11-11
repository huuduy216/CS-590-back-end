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

import com.example.spacecode.model.Implementation;
import com.example.spacecode.service.ImplementationService;

@CrossOrigin(origins = "*")
@RestController
public class ImplementationController {
	 @Autowired
	 private ImplementationService implementationService;

	// add
	@RequestMapping(value = "/implementation", method = RequestMethod.POST)
	public Implementation save(@RequestBody Map<String, String> map ) throws AuthenticationException {
		System.out.println("Hello");
		if(map.containsKey("name")) {
			System.out.println("Calling service");
			String name = map.get("name");
			String testcase = map.get("testcase");
		
			if (implementationService.get(name) != null) return null;
	        implementationService.save(name, testcase);
	        return implementationService.get(name);
	    }
	    return null;
	}
	
	// get
	@RequestMapping(value = "/implementation/{name}", method = RequestMethod.GET)
	public Implementation get(@PathVariable String name,String testcase ) throws AuthenticationException {
		System.out.println("Fetching: " + name + testcase);
		System.out.println(implementationService.get(name));
		return implementationService.get(name);
	}
	
	// get
	@RequestMapping(value = "/implementation/getAll", method = RequestMethod.GET)
	public List<Implementation> get() throws AuthenticationException { 
		return implementationService.get();
	}
}
