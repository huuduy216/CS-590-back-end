package com.example.spacecode;

import com.example.spacecode.model.Role;
import org.junit.jupiter.api.Test;

import com.example.spacecode.model.User;

import static org.junit.Assert.*;

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
		u1.setRole(new Role((long) 11, "test role"));
		assertEquals(u1.getRoles().getName(), "test role");
	}

	@Test
	public void testToString() {
		User u1 = new User(USER_NAME, PASS);
		u1.setIdRegisteredUser(123);
		assertEquals("User {idClassification=123, userName=" + USER_NAME + "}", u1.toString());
	}

	@Test
	public void equalstest() {
		User user1 = new User("abc","123");
		User user2 = new User("abc","123");
		User user3 = new User("asdf","456");
		user3.setIdRegisteredUser(3);

		assertEquals(user1,user2);
		assertNotEquals(user1,user3);
	}

	@Test
	void permitTest() {
		User u = new User("test", "pass");
		assertTrue(u.isAccountNonExpired());
		assertTrue(u.isAccountNonLocked());
		assertTrue(u.isCredentialsNonExpired());
	}
}
