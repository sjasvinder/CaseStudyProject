package com.jasvindersingh.airlinebookingsystem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jasvindersingh.airlinebookingsystem.constants.AppConstants;
import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Airline;
import com.jasvindersingh.airlinebookingsystem.models.Booking;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.services.IBookingService;
import com.jasvindersingh.airlinebookingsystem.services.IHotelService;

@RestController
@CrossOrigin("*")
@RequestMapping("/booking")
public class BookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private IBookingService bookingService;
	
	@Autowired
	private IHotelService hotelService;
	
	@PostMapping("/populateAddBooking")
	public Map<String,String> populateAddBooking(@RequestBody Map<String,Object> mapData,HttpSession session) throws AppException {
		logger.info("In populateAddBooking with " + mapData);
		User user = (User)session.getAttribute("user");
		Airline airline = convertListToAirline((Map<String,Object>)mapData.get("airline"));
		ModelAndView mav = new ModelAndView(AppConstants.PAGE_ALL_BOOKING);
		Booking booking = new Booking();
		booking.setAirline(airline);
		booking.setAirlineName(airline.getAirlineName());
		booking.setUser(user);
		booking.setDateFrom(mapData.get("dateFrom").toString());
		booking.setDateTo(mapData.get("dateTo").toString());
		booking.setWayType(mapData.get("wayType").toString());
		booking.setArrHostel(hotelService.getHotelsByPlace(airline.getPlaceTo()));
		session.setAttribute("currentBooking", booking);
		Map<String,String> mapRes = new HashMap<String,String>();
		mapRes.put("page",AppConstants.PAGE_ALL_BOOKING);
		mav.addObject("loggedUser", session.getAttribute("user"));
		mav.addObject("isAdmin", session.getAttribute("isAdmin"));
		return mapRes;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute("booking") Booking booking,BindingResult result,HttpSession session) throws AppException {
		try {
			if (result.hasErrors()) {
				ModelAndView mav = new ModelAndView(AppConstants.PAGE_ALL_BOOKING);
				mav.addObject("result", result);
				mav.addObject("hotel",booking);
				return mav;
	        }
			if(session.getAttribute("currentBooking") != null) {
				String hotelName = booking.getHotelName();
				booking = (Booking)session.getAttribute("currentBooking");
				booking.setHotelName(hotelName);
				session.removeAttribute("currentBooking");
			}
			User user = (User)session.getAttribute("user");
			booking.setUser(user);
			Hotel hotel = hotelService.getByHotelName(booking.getHotelName());
			booking.setHotel(hotel);
			booking = bookingService.save(booking);
			List<Booking> lst = bookingService.getAllByUserID(user.getId());
			ModelAndView mav = new ModelAndView(AppConstants.PAGE_ALL_BOOKING);
			mav.addObject("booking",new Booking());
			mav.addObject("bookingList",lst);
			mav.addObject("loggedUser", session.getAttribute("user"));
			mav.addObject("isAdmin", session.getAttribute("isAdmin"));
			return mav;
		}
		catch(AppException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Booking> delete(@RequestBody Map<String,String> mapBooking) throws AppException {
		try {
			return ResponseEntity.ok(bookingService.delete(Long.parseLong(mapBooking.get("id").toString())));
		}
		catch(AppException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Booking>> getAllHotels(HttpSession session) throws AppException {
		try {
			User user = (User)session.getAttribute("user");
			List<Booking> lst = bookingService.getAllByUserID(user.getId());
			return ResponseEntity.ok(lst);
		}
		catch(AppException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private Airline convertListToAirline(Map<String,Object> mapAirline) {
		Airline airline = new Airline(Long.parseLong(mapAirline.get("id").toString()),mapAirline.get("airlineName").toString(),mapAirline.get("placeFrom").toString(),mapAirline.get("placeTo").toString(),mapAirline.get("phone").toString());
		return airline;
	}
}
