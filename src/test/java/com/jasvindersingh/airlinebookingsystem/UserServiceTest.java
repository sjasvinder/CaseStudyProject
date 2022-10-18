package com.jasvindersingh.airlinebookingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Airline;
import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.repositories.AirlineRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.UserRepository;
import com.jasvindersingh.airlinebookingsystem.services.AirlineServiceImpl;
import com.jasvindersingh.airlinebookingsystem.services.UsersServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository repo;
	
	@Autowired
    @InjectMocks
    private UsersServiceImpl userService;
	
	@BeforeEach
    public void setUp() {
		
	}
	
	@Test
	public void testCreateAirline() throws AppException {
		User user = new User();
		user.setName("Jasvinder");
		user.setEmail("jasvinder@jasvinder.com");
		user.setPassword("jasvinder2022");
		user.setPhoneNumber("1234567890");
		when(repo.save(any())).thenReturn(user);
		userService.saveUser(user);
	     verify(repo,times(1)).save(any());

	}
}
