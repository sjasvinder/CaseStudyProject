package com.jasvindersingh.airlinebookingsystem.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jasvindersingh.airlinebookingsystem.constants.AppConstants;
import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Hotel;
import com.jasvindersingh.airlinebookingsystem.services.IHotelService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hotels")
public class HotelController {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
	@Autowired
	private IHotelService hotelService;
	
	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute("hotel") Hotel hotel,BindingResult result,HttpSession session) throws AppException {
		logger.info("In Hotel save method with Hotel " + hotel);
		try {
			if (result.hasErrors()) {
				ModelAndView mav = new ModelAndView(AppConstants.PAGE_HOTEL);
				mav.addObject("result", result);
				mav.addObject("hotel",hotel);
				mav.addObject("loggedUser", session.getAttribute("user"));
				mav.addObject("isAdmin", session.getAttribute("isAdmin"));
				return mav;
	        }
			hotel = hotelService.save(hotel);
			List<Hotel> lst = hotelService.getAll();
			ModelAndView mav = new ModelAndView(AppConstants.PAGE_HOTEL);
			mav.addObject("hotel",new Hotel());
			mav.addObject("hotelList",lst);
			mav.addObject("loggedUser", session.getAttribute("user"));
			mav.addObject("isAdmin", session.getAttribute("isAdmin"));
			return mav;
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured while saving Hotel",e);
			throw e;
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Hotel> delete(@RequestBody Map<String,String> mapUser) throws AppException {
		logger.info("In Hotel delete method with Hotel " + mapUser);
		try {
			Hotel hotel = new Hotel(Long.parseLong(mapUser.get("id").toString()),mapUser.get("hotelName"),mapUser.get("place"),mapUser.get("phone"));
			return ResponseEntity.ok(hotelService.delete(hotel));
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured while delete Hotel",e);
			throw e;
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Hotel>> getAllHotels() throws AppException {
		logger.info("In Hotel getAllHotels method");
		try {
			List<Hotel> lst = hotelService.getAll();
			return ResponseEntity.ok(lst);
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured in getAllHotels",e);
			throw e;
		}
	}
	
	@GetMapping("/byplace")
	public ResponseEntity<List<Hotel>> getHotelsByPlace(@RequestParam(name = "place") String place) throws AppException {
		logger.info("In Hotel getHotelsByPlace method");
		try {
			List<Hotel> lst = hotelService.getHotelsByPlace(place);
			return ResponseEntity.ok(lst);
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured in getHotelsByPlace",e);
			throw e;
		}
	}
}
