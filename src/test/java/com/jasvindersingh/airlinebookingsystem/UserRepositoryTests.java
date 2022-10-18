package com.jasvindersingh.airlinebookingsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class UserRepositoryTests {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setName("Jasvinder");
		user.setEmail("jasvinder@jasvinder.com");
		user.setPassword("jasvinder2022");
		user.setPhoneNumber("1234567890");
		User savedUser = repo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	}
	
	@Test
	public void testFindByEmail() {
		String email = "jasvin@jasvin.com";
		User foundUser = repo.findByEmail(email);
		
		assertThat(foundUser.getEmail()).isEqualTo(email);
	}
}
