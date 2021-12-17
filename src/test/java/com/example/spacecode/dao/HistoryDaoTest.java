package com.example.spacecode.dao;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.spacecode.dao.HistoryDao;
import com.example.spacecode.model.History;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HistoryDaoTest {
//	@Test
//	public void testSomething() { 
//	      ArrayList<History> historyTest =new ArrayList<>();
//	     for(int i =0;i<historyTest.size();i++)
//	     {
//	    	 
//	     }
//	     ArrayList<History> histories = HistoryDao.getHistory();
//	     for (int i = 0; i < histories.size(); i++) {
//	         assertEquals(histories.get(i), historyTest.get(i));
//	     }
//	}
	 @Autowired
	    private HistoryDao repo;
	@Test
	public void historysaveTest()
	{
		History history = repo.save(new History("abc","cde"));
		assertEquals("abc", history.getUserName());
	}
	
	@Test
	public void getHistoryTest()
	{

		  ArrayList<History> History =(ArrayList<History>) repo.getHistory();
		  assertThat(History).size().isGreaterThan(0);
	}

}
