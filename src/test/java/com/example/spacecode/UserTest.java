package com.example.spacecode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.User;

public class UserTest {
	private static String USER_NAME = "admin";
	private static String PASS = "password";
	
	@Test
	public void testBasicConstructor() {
		User u1 = new User(USER_NAME, PASS);
		assertEquals(USER_NAME, u1.getUserName());
		assertEquals(PASS, u1.getPassword());
	}

	@Test
	public void testAdminConstructor() {
		User u1 = new User(USER_NAME, PASS, false);
		User u2 = new User(USER_NAME, PASS, true);
		assertNotEquals("ROLE_ADMIN", u1.getRoles().getName());
		assertEquals("ROLE_ADMIN", u2.getRoles().getName());
	}
	
	
	@Test
	public void testSetter() {
		User u1 = new User(USER_NAME, PASS);
		u1.setPassword("changing");
		assertEquals("changing", u1.getPassword());
		u1.setUserName("admin1");
		assertEquals("admin1", u1.getUsername());
		u1.setIdRegisteredUser(99);
		assertEquals(99, u1.getIdRegisteredUser());
	}

	@Test
	public void testToString() {
		User u1 = new User(USER_NAME, PASS);
		u1.setIdRegisteredUser(123);
		assertEquals("User {idClassification=123, userName=" + USER_NAME + "}", u1.toString());
	}

}
