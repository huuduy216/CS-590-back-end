package com.example.spacecode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.Role;

public class RoleTest {
	
	@Test
	public void roleConstructor()
	{
		Role r1 = new Role((long) 11,"abc");
		assertEquals("abc",r1.getName());
		assertNotEquals("asdf", r1.getName());
	}
	@Test
	public void testset()
	{
		Role r1 = new Role((long) 11,"abc");
		r1.setName("abc");
		assertEquals("abc",r1.getName());
		r1.setId((long) 11);
		assertEquals((long)11, (long) r1.getId());
	}

}
