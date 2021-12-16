package com.example.spacecode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.Classification;


public class ClassificationTest {
	
	@Test
	void ClassficationConstructorTest()
	{
		Classification c1 = new Classification("abc","cde","efg","ghi");
		assertEquals("abc",c1.getName());
		assertEquals("cde",c1.getIdentitykey());
		assertEquals("efg",c1.getDesription());
		assertEquals("ghi",c1.getSubtitle());
	}
	
	@Test
	void ClassificationgettersetterTest()
	{
		Classification c1= new Classification();
		c1.setName("abc");
		c1.setIdentitykey("cde");
		c1.setDesription("efg");
		c1.setSubtitle("ghi");
		c1.setIdClassification(1);
		assertEquals("abc",c1.getName());
		assertEquals("cde",c1.getIdentitykey());
		assertEquals("efg",c1.getDesription());
		assertEquals("ghi",c1.getSubtitle());
		assertEquals(1,c1.getIdClassification());
		
	}
	

}
