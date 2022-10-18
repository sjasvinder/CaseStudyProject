package com.jasvindersingh.airlinebookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements IHotelService {
	@Autowired
	private HotelRepository hotelRepo;
	
	public Hotel save(Hotel hotel) throws AppException {
		Hotel retHotel = hotelRepo.save(hotel);
		return retHotel;
	}
	
	public Hotel delete(Hotel hotel) throws AppException {
		hotelRepo.deleteById(hotel.getId());
		return hotel;
	}
	
	public List<Hotel> getAll() throws AppException {
		List<Hotel> lst = hotelRepo.findAll();
		return lst;
	}
	
	public List<Hotel> getHotelsByPlace(String place) throws AppException {
		List<Hotel> lst = hotelRepo.findByPlace(place);
		return lst;
	}
	
	public Hotel getByHotelName(String hotelName) throws AppException {
		Hotel hotel = hotelRepo.findByHotelName(hotelName);
		return hotel;
	}
	
	

}
