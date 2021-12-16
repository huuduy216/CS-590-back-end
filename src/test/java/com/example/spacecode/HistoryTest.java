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
}
