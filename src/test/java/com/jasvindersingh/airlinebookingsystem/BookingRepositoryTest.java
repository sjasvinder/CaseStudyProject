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
import com.jasvindersingh.airlinebookingsystem.models.Booking;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.repositories.AirlineRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.BookingRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.HotelRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class BookingRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private AirlineRepository airlineRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Test
	public void testCreateBooking() throws AppException {
		Booking booking = getBookingVO(1);
		Booking savedBooking = bookingRepo.save(booking);
		Booking existBooking = entityManager.find(Booking.class, savedBooking.getId());
		assertNotNull(existBooking);
	}
	
	@Test
	public void testDeleteBooking() throws AppException {
		Booking booking = getBookingVO(2);
		Booking savedBooking = bookingRepo.save(booking);
		bookingRepo.delete(savedBooking);
		Booking existBooking = entityManager.find(Booking.class, savedBooking.getId());
		assertNull(existBooking);
	}
	
	@Test
	public void testGetAllByUserID() throws AppException {
		Booking booking = getBookingVO(3);
		Booking savedBooking = bookingRepo.save(booking);
		List<Booking> lst = bookingRepo.findByUserId(booking.getUser().getId());
		assertTrue(lst.size() > 0);
	}
	
	private Booking getBookingVO(int count) throws AppException {
		User user = new User();
		user.setName("Jasvinder" + count);
		user.setEmail("jasvinder@jasvinder.com" + count);
		user.setPassword("jasvinder2022");
		user.setPhoneNumber("1234567890");
		User savedUser = userRepo.save(user);
		Hotel hotel = new Hotel(null,"Hotel 2" + count,"Newyork","1111111111");
		Hotel savedHotel = hotelRepo.save(hotel);
		Airline airline = new Airline(null,"Airline 2" + count,"Newyork","New Delhi","1111111111");
		Airline savedAirline = airlineRepo.save(airline);
		Booking booking = new Booking(null,savedUser,savedAirline,savedHotel,"2022-10-09","2022-10-14","Round Trip");
		return booking;
	}

}
