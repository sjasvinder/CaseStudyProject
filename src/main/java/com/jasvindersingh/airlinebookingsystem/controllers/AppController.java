package com.jasvindersingh.airlinebookingsystem.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jasvindersingh.airlinebookingsystem.constants.AppConstants;
import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Airline;
import com.jasvindersingh.airlinebookingsystem.models.Booking;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.services.IAirlineService;
import com.jasvindersingh.airlinebookingsystem.services.IBookingService;
import com.jasvindersingh.airlinebookingsystem.services.IHotelService;

@Controller
public class AppController {
	@Autowired
	private IHotelService hotelService;
	
	@Autowired
	private IAirlineService airlineService;
	
	@Autowired
	private IBookingService bookingService;
	
	@RequestMapping(value="/index")
	public String index() {
		return AppConstants.PAGE_INDEX;
	}
	
	@RequestMapping(value="/login")
	public String login(Model model) {
		model.addAttribute("user",new User());
		return AppConstants.PAGE_LOGIN;
	}
	
	@RequestMapping(value="/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		return AppConstants.PAGE_REGISTRATION;
	}
	
	@RequestMapping(value="/hotel")
	public String hotel(Model model,HttpSession session) throws AppException {
		if(session.getAttribute("user") != null) {
			List<Hotel> lst = hotelService.getAll();
			model.addAttribute("hotel",new Hotel());
			model.addAttribute("hotelList",lst);
			model.addAttribute("loggedUser", session.getAttribute("user"));
			model.addAttribute("isAdmin", session.getAttribute("isAdmin"));
			return AppConstants.PAGE_HOTEL;
		}
		return ("forward:/" + AppConstants.PAGE_LOGIN);
	}
	
	@RequestMapping(value="/airline")
	public String airline(Model model,HttpSession session) throws AppException {
		if(session.getAttribute("user") != null) {
			List<Airline> lst = airlineService.getAll();
			model.addAttribute("airline",new Airline());
			model.addAttribute("airlineList",lst);
			model.addAttribute("loggedUser", session.getAttribute("user"));
			model.addAttribute("isAdmin", session.getAttribute("isAdmin"));
			return AppConstants.PAGE_AIRLINE;
		}
		return ("forward:/" + AppConstants.PAGE_LOGIN);
	}
	
	@RequestMapping(value="/booking")
	public String newbooking(Model model,HttpSession session) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("loggedUser", session.getAttribute("user"));
			model.addAttribute("isAdmin", session.getAttribute("isAdmin"));
			return AppConstants.PAGE_BOOKING;
		}
		return ("forward:/" + AppConstants.PAGE_LOGIN);
	}
	
	@RequestMapping(value="/allbookings")
	public ModelAndView bookings(HttpSession session ) throws AppException {
		if(session.getAttribute("user") != null) {
			ModelAndView mav = new ModelAndView(AppConstants.PAGE_ALL_BOOKING);
			if(session.getAttribute("currentBooking") != null) {
				mav.addObject("booking", (Booking)session.getAttribute("currentBooking"));
				//session.removeAttribute("currentBooking");
			}
			else {
				mav.addObject("booking",new Booking());
			}
			User user = (User)session.getAttribute("user");
			List<Booking> lst = bookingService.getAllByUserID(user.getId());
			mav.addObject("bookingList",lst);
			mav.addObject("loggedUser", session.getAttribute("user"));
			mav.addObject("isAdmin", session.getAttribute("isAdmin"));
			return mav;
		}
		ModelAndView mav = new ModelAndView(AppConstants.PAGE_LOGIN);
		mav.addObject("user",new User());
		return mav;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
			session.removeAttribute("isAdmin");
			session.removeAttribute("roles");
		}
		return ("forward:/" + AppConstants.PAGE_INDEX);
	}
}
