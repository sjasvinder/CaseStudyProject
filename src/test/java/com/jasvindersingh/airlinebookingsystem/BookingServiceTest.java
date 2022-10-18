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
import com.jasvindersingh.airlinebookingsystem.models.Booking;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.repositories.AirlineRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.BookingRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.HotelRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.UserRepository;
import com.jasvindersingh.airlinebookingsystem.services.AirlineServiceImpl;
import com.jasvindersingh.airlinebookingsystem.services.BookingServiceImpl;
import com.jasvindersingh.airlinebookingsystem.services.HotelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
	@Mock
	private BookingRepository bookingRepo;
	
	@Autowired
    @InjectMocks
    private BookingServiceImpl bookingService;
	
	@BeforeEach
    public void setUp() {
		
	}
	
	@Test
	public void testCreateAirline() throws AppException {
		User user = new User();
		user.setName("Jasvinder 1");
		user.setEmail("jasvinder@jasvinder.com1");
		user.setPassword("jasvinder2022");
		user.setPhoneNumber("1234567890");
		Hotel hotel = new Hotel(null,"Hotel 10","Newyork","1111111111");
		Airline airline = new Airline(null,"Airline 20","Newyork","New Delhi","1111111111");
		Booking booking = new Booking(null,user,airline,hotel,"2022-10-09","2022-10-14","Round Trip");
		when(bookingRepo.save(any())).thenReturn(booking);
		bookingService.save(booking);
	     verify(bookingRepo,times(1)).save(any());

	}
}
