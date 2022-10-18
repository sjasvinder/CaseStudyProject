package com.jasvindersingh.airlinebookingsystem.services;

import java.util.List;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Airline;

public interface IAirlineService {
	Airline save(Airline airline) throws AppException;
	Airline delete(Airline airline) throws AppException;
	List<Airline> getAll() throws AppException;
	List<Airline> getAirlineByPlace(String placeFrom,String placeTo) throws AppException;
}
