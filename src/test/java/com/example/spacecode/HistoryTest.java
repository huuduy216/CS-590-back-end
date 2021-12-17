package com.example.spacecode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.History;
import com.example.spacecode.model.User;


public class HistoryTest {

		@Test
		public void HistorysetterTest()
		{
			History h1 = new History("abc","cde");
			h1.setUserName("abc");
			assertEquals("abc", h1.getUserName());
			h1.setHistory("cde");
			assertEquals("cde", h1.getHistory());
		}
		@Test
		public void testToString() {
			History h1 = new History("abc", "cde");
			h1.setHistory("abc");
			
			String history = null;
			String userName = null;
			assertNotEquals("History {history=" + history + ", userName=" + userName + "}",h1.toString());
		}

	@Test
	public void equalstest() {
		//History test for equals
		History h1 = new History("abc","123");
		History h2 = new History("abc","123");
		History h3 = new History("abdc","123");

		assertEquals(h1,h2);
		assertNotEquals(h1,h3);
	}
}
