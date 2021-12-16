package com.example.spacecode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.Implementation;
public class ImplementationTest {
	
	@Test
	void implementationsetterTest()
	{
		Implementation i1 = new Implementation("java","abc","cde","efg",1);
		i1.setLanguageName("java");
		i1.setSubtitle("abc");
		i1.setDescription("cde");
		i1.setCode("efg");
		i1.setId_Algorithm(1);
		assertEquals("java", i1.getLanguageName());
		assertEquals("abc",i1.getSubtitle());
		assertEquals("cde", i1.getDescription());
		assertEquals("efg", i1.getCode());
		assertEquals(1, i1.getId_Algorithm());
		i1.setIdImplementation(1);
		assertEquals(1, i1.getIdImplementation());
	}

}
