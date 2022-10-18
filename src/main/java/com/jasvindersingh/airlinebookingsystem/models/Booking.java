package com.jasvindersingh.airlinebookingsystem.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="bookings")
public class Booking {
	
	public Booking(Long id, User user, Airline airline, Hotel hotel, String dateFrom, String dateTo,String wayType) {
		this.id = id;
		this.user = user;
		this.airline = airline;
		this.hotel = hotel;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.wayType = wayType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private Airline airline;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
	
	@Column(name = "date_from", columnDefinition = "Date")
	private String dateFrom;
	
	@Column(name = "date_to", columnDefinition = "Date")
	private String dateTo;
	
	@Column(length=20,nullable = false)
	private String wayType;
	
	@Transient
	private List<Hotel> arrHostel;
	
	@Transient 
	private String hotelName;
	
	@Transient 
	private String airlineName;
}
