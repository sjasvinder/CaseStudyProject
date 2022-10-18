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

import com.jasvindersingh.airlinebookingsystem.AirlineBookingSystemApplication;
import com.jasvindersingh.airlinebookingsystem.constants.AppConstants;
import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.Airline;
import com.jasvindersingh.airlinebookingsystem.services.IAirlineService;

@RestController                     // initiates RESTFull web services 
@CrossOrigin("*")                   // enable cross-origin resource sharing only for this method by default it allows all origin
@RequestMapping("/airlines")
public class AirlineController {
	
	private static final Logger logger = LoggerFactory.getLogger(AirlineController.class);
	
	@Autowired
	private IAirlineService airlineService;
	
	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute("airline") Airline airline,BindingResult result,HttpSession session) throws AppException {
		logger.info("In airlines save method with airline " + airline);
		try {
			if (result.hasErrors()) {
				ModelAndView mav = new ModelAndView(AppConstants.PAGE_AIRLINE);
				mav.addObject("result", result);
				mav.addObject("airline",airline);
				mav.addObject("loggedUser", session.getAttribute("user"));
				mav.addObject("isAdmin", session.getAttribute("isAdmin"));
				return mav;
	        }
			airline = airlineService.save(airline);
			List<Airline> lst = airlineService.getAll();
			ModelAndView mav = new ModelAndView(AppConstants.PAGE_AIRLINE);
			mav.addObject("airline",new Airline());
			mav.addObject("airlineList",lst);
			mav.addObject("loggedUser", session.getAttribute("user"));
			mav.addObject("isAdmin", session.getAttribute("isAdmin"));
			logger.info("airlines save method ends");
			return mav;
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured while saving Airline",e);
			throw e;
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Airline> delete(@RequestBody Map<String,String> mapUser) throws AppException {
		logger.info("In airlines delete method with args " + mapUser);
		try {
			Airline airline = new Airline(Long.parseLong(mapUser.get("id").toString()),mapUser.get("airlineName"),mapUser.get("placeFrom"),mapUser.get("placeTo"),mapUser.get("phone"));
			return ResponseEntity.ok(airlineService.delete(airline));
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured while deleting Airline",e);
			throw e;
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Airline>> getAllAirlines() throws AppException {
		logger.info("In airlines getAllAirlines method");
		try {
			List<Airline> lst = airlineService.getAll();
			return ResponseEntity.ok(lst);
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured while retreiving Airline",e);
			throw e;
		}
	}
	
	@GetMapping("/allByPlace")
	public ResponseEntity<List<Airline>> getAirlineByPlace(@RequestParam(name = "placeFrom") String placeFrom,@RequestParam(name = "placeTo") String placeTo) throws AppException {
		logger.info("In airlines allByPlace method");
		try {
			List<Airline> lst = airlineService.getAirlineByPlace(placeFrom,placeTo);
			return ResponseEntity.ok(lst);
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured while retreiving Airline By Place",e);
			throw e;
		}
	}
}
