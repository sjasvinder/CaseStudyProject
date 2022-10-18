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
import com.jasvindersingh.airlinebookingsystem.repositories.AirlineRepository;
import com.jasvindersingh.airlinebookingsystem.services.AirlineServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AirlineServiceTest {
	@Mock
	private AirlineRepository airlineRepo;
	
	@Autowired
    @InjectMocks
    private AirlineServiceImpl airlineService;
	
	@BeforeEach
    public void setUp() {
		
	}
	
	@Test
	public void testCreateAirline() throws AppException {
		Airline airline = new Airline(null,"Airline 10","Newyork","New Delhi","1111111111");
		when(airlineRepo.save(any())).thenReturn(airline);
		airlineService.save(airline);
	     verify(airlineRepo,times(1)).save(any());

	}
}
