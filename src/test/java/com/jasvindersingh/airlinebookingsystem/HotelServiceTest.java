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
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.repositories.AirlineRepository;
import com.jasvindersingh.airlinebookingsystem.repositories.HotelRepository;
import com.jasvindersingh.airlinebookingsystem.services.AirlineServiceImpl;
import com.jasvindersingh.airlinebookingsystem.services.HotelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {
	@Mock
	private HotelRepository hotelRepo;
	
	@Autowired
    @InjectMocks
    private HotelServiceImpl hotelService;
	
	@BeforeEach
    public void setUp() {
		
	}
	
	@Test
	public void testCreateAirline() throws AppException {
		Hotel hotel = new Hotel(null,"Hotel 10","Newyork","1111111111");
		when(hotelRepo.save(any())).thenReturn(hotel);
		hotelService.save(hotel);
	     verify(hotelRepo,times(1)).save(any());

	}
}
