package com.jasvindersingh.airlinebookingsystem.services;

import java.util.List;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;

public interface IHotelService {
	Hotel save(Hotel hotel) throws AppException;
	Hotel delete(Hotel hotel) throws AppException;
	List<Hotel> getAll() throws AppException;
	List<Hotel> getHotelsByPlace(String place) throws AppException;
	Hotel getByHotelName(String hotelName) throws AppException;
}
