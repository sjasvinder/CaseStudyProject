package com.jasvindersingh.airlinebookingsystem.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jasvindersingh.airlinebookingsystem.models.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
	@Query("SELECT h FROM Hotel h WHERE h.place = ?1")
	public List<Hotel> findByPlace(String place);
	
	@Query("SELECT h FROM Hotel h WHERE h.hotelName = ?1")
	public Hotel findByHotelName(String hotelName);
}
