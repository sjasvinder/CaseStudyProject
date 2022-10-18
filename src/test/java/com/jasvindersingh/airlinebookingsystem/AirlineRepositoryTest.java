package com.jasvindersingh.airlinebookingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Airline;
import com.jasvindersingh.airlinebookingsystem.repositories.AirlineRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class AirlineRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private AirlineRepository airlineRepo;
	
	@Test
	public void testCreateAirline() throws AppException {
		Airline airline = new Airline(null,"Airline 10","Newyork","New Delhi","1111111111");
		Airline savedAirline = airlineRepo.save(airline);
		Airline existAirline = entityManager.find(Airline.class, savedAirline.getId());
		assertEquals(airline.getAirlineName(), existAirline.getAirlineName());
	}
	
	@Test
	public void testDeleteAirline() throws AppException {
		Airline airline = new Airline(null,"Airline 11","Newyork","New Delhi","1111111111");
		Airline savedAirline = airlineRepo.save(airline);
		airlineRepo.delete(savedAirline);
		Airline existAirline = entityManager.find(Airline.class, savedAirline.getId());
		assertNull(existAirline);
	}
	
	@Test
	public void testGetAllAirlines() throws AppException {
		Airline airline = new Airline(null,"Airline 12","Newyork","New Delhi","1111111111");
		Airline savedAirline = airlineRepo.save(airline);
		List<Airline> lst = airlineRepo.findAll();
		assertTrue(lst.size() > 0);
	}
	
	@Test
	public void testGetAirlineByPlace() throws AppException {
		Airline airline = new Airline(null,"Airline 13","Newyork","New Delhi","1111111111");
		Airline savedAirline = airlineRepo.save(airline);
		List<Airline> lst = airlineRepo.findByPlaceFromAndPlaceTo("Newyork","New Delhi");
		assertTrue(lst.size() > 0);
	}

}
