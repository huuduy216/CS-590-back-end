package com.example.spacecode.dao;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.spacecode.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserDaoTest {

	 @Autowired
	    private UserDao repo;
	 
	 @Test
	 @Rollback(false)
	 public void UserDaoTest()
	 {
		 User User = repo.save(new User("username","password"));
		 assertEquals("username",User.getUsername());
	 }
	 
	 @Test
	 public void testfindUser()
	 {
		 User user = repo.findbyname("username");
		 assertThat(user.getUsername()).isEqualTo("username");
	 }
}
