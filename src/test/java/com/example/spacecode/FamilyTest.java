package com.example.spacecode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.Family;


public class FamilyTest {
	
	@Test
	public void FamilySetterTest()
	{
		Family f1 = new Family();
		f1.setName("abc");
		assertNotEquals("cde", f1.getName());
		f1.setSubtitle("cde");
		assertEquals("cde",f1.getSubtitle());
		f1.setIdentitykey("abc123");
		assertEquals("abc123",f1.getIdentitykey());
		f1.setDesription("asdadsf");
		assertNotEquals("sd fasdf ",f1.getDesription());
		f1.setIdFamily(1);
		assertNotEquals(2,f1.getIdFamily());
	}

	@Test
	public void FamilyConstructor()
	{
		Family f1 = new Family("abc","cde","abc123","asd");
		f1.setName("abc");
		assertEquals("abc",f1.getName());
		f1.setSubtitle("cde");
		assertEquals("cde",f1.getSubtitle());
		f1.setIdentitykey("abc123");
		assertEquals("abc123",f1.getIdentitykey());
		f1.setDesription("asd");
		assertEquals("asd",f1.getDesription());
	}
}
