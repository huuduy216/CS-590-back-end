package com.example.spacecode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.Algorithm;

public class AlgorithmTest {

	@Test
	public void AlgorithmConstructor()
	{
		Algorithm a1 = new Algorithm("name","subtitle","identity","description");
		assertEquals("name", a1.getName());
		assertEquals("subtitle",a1.getSubtitle());
		assertEquals("identity",a1.getIdentitykey());
		assertEquals("description",a1.getDesription());
		
		
	}
	@Test
	public void AlgorithmGetterSetterTest()
	{
		Algorithm a1 = new Algorithm();
		a1.setName("name");
		a1.setSubtitle("subtitle");
		a1.setIdentitykey("identity");
		a1.setDesription("description");
		a1.setIdAlgorithm(1);
		assertEquals("name", a1.getName());
		assertEquals("subtitle",a1.getSubtitle());
		assertEquals("identity",a1.getIdentitykey());
		assertEquals("description",a1.getDesription());
		assertEquals(1,a1.getIdAlgorithm());
	}
}
