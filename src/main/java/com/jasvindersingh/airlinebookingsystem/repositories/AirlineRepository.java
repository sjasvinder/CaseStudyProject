package com.jasvindersingh.airlinebookingsystem.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jasvindersingh.airlinebookingsystem.models.Airline;

public interface AirlineRepository extends JpaRepository<Airline,Long> {          // entity class Airline with primary key Datatype
	@Query("SELECT a FROM Airline a WHERE a.placeFrom = ?1 and a.placeTo = ?2")
	public List<Airline> findByPlaceFromAndPlaceTo(String placeFrom,String placeTo);
}
