package com.jasvindersingh.airlinebookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Airline;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.repositories.AirlineRepository;

@Service
public class AirlineServiceImpl implements IAirlineService {
	@Autowired
	private AirlineRepository airlineRepo;
	
	public Airline save(Airline airline) throws AppException {
		Airline retAirline = airlineRepo.save(airline);
		return retAirline;
	}
	
	public Airline delete(Airline airline) throws AppException {
		airlineRepo.deleteById(airline.getId());
		return airline;
	}
	
	public List<Airline> getAll() throws AppException {
		List<Airline> lst = airlineRepo.findAll();
		return lst;
	}
	
	public List<Airline> getAirlineByPlace(String placeFrom,String placeTo) throws AppException {
		List<Airline> lst = airlineRepo.findByPlaceFromAndPlaceTo(placeFrom,placeTo);
		return lst;
	}

}
