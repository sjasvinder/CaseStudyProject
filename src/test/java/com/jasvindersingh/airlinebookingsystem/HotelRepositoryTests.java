package com.jasvindersingh.airlinebookingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.repositories.HotelRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class HotelRepositoryTests {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Test
	public void testCreateHotel() throws AppException {
		Hotel hotel = new Hotel(null,"Hotel 10","Newyork","1111111111");
		Hotel savedHotel = hotelRepo.save(hotel);
		Hotel existHotel = entityManager.find(Hotel.class, savedHotel.getId());
		assertEquals(hotel.getHotelName(), existHotel.getHotelName());
	}
	
	@Test
	public void testDeleteHotel() throws AppException {
		Hotel hotel = new Hotel(null,"Hotel 11","Newyork","1111111111");
		Hotel savedHotel = hotelRepo.save(hotel);
		hotelRepo.delete(savedHotel);
		Hotel existHotel = entityManager.find(Hotel.class, savedHotel.getId());
		assertNull(existHotel);
	}
	
	@Test
	public void testGetAllHotel() throws AppException {
		Hotel hotel = new Hotel(null,"Hotel 12","Newyork","1111111111");
		Hotel savedHotel = hotelRepo.save(hotel);
		List<Hotel> lst = hotelRepo.findAll();
		assertTrue(lst.size() > 0);
	}
	
	@Test
	public void testGetHotelByPlace() throws AppException {
		Hotel hotel = new Hotel(null,"Hotel 13","Newyork","1111111111");
		Hotel savedHotel = hotelRepo.save(hotel);
		List<Hotel> lst = hotelRepo.findByPlace("Newyork");
		assertTrue(lst.size() > 0);
	}
	
	@Test
	public void testGetHotelByHotelName() throws AppException {
		Hotel hotel = new Hotel(null,"Hotel 14","Newyork","1111111111");
		Hotel savedHotel = hotelRepo.save(hotel);
		Hotel hotel1 = hotelRepo.findByHotelName("Hotel 14");
		assertNotNull(hotel1);
	}
}
