package com.jasvindersingh.airlinebookingsystem.services;

import java.util.List;
import java.util.Map;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Booking;

public interface IBookingService {
	Booking save(Booking booking) throws AppException;
	Booking delete(Long bookingId) throws AppException;
	List<Booking> getAllByUserID(Long userId) throws AppException;
}	
