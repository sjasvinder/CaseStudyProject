package com.jasvindersingh.airlinebookingsystem.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Booking;
import com.jasvindersingh.airlinebookingsystem.repositories.BookingRepository;

@Service
public class BookingServiceImpl implements IBookingService {
	@Autowired
	private BookingRepository bookingRepo;
	
	public Booking save(Booking booking) throws AppException {
		Booking retHotel = bookingRepo.save(booking);
		return retHotel;
	}
	
	public Booking delete(Long bookingId) throws AppException {
		Booking booking = bookingRepo.findById(bookingId).get();
		bookingRepo.deleteById(bookingId);
		return booking;
	}
	
	public List<Booking> getAllByUserID(Long userId) throws AppException {
		List<Booking> lst = bookingRepo.findByUserId(userId);
		return lst;
	}
}
