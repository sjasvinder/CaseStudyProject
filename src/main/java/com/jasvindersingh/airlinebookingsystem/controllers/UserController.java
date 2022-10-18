package com.jasvindersingh.airlinebookingsystem.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jasvindersingh.airlinebookingsystem.constants.AppConstants;
import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.services.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;
	
	@PostMapping("/add")
	public ModelAndView addUser(@Valid @ModelAttribute("user") User user,BindingResult result) throws AppException {
		logger.info("In user addUser method with user " + user);
		try {
			if (result.hasErrors()) {
				ModelAndView mav = new ModelAndView(AppConstants.PAGE_REGISTRATION);
				mav.addObject("result", result);
				mav.addObject("user",user);
				return mav;
	        }
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			User newUser = userService.saveUser(user);
			return new ModelAndView("forward:/" + AppConstants.PAGE_LOGIN);
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured in getHotelsByPlace",e);
			throw e;
		}
	}
	
	@PostMapping("/authenticate")
	public ModelAndView authenticate(@Valid @ModelAttribute("user") User user,BindingResult result,HttpSession session) throws AppException {
		logger.info("In user authenticate method with user " + user);
		try {
			User newUser = userService.authenticate(user);
			if(newUser == null) {
				ModelAndView mav = new ModelAndView("forward:/" + AppConstants.PAGE_LOGIN);
				mav.addObject("invalidDetails", "Either the Username or Password is incorrect");
				mav.addObject("user",user);
				return mav;
			}
			session.setAttribute("user", newUser);
			boolean isAdmin = false;
			List<String> lstRole = new ArrayList<String>();
			if("ADMIN".equals(newUser.getRole())) {
				lstRole.add("ADMIN");
				isAdmin = true;
	    	}
			lstRole.add("USER");
			session.setAttribute("roles", lstRole);
			session.setAttribute("isAdmin", isAdmin);
			return new ModelAndView("forward:/" + AppConstants.PAGE_BOOKING);
		}
		catch(AppException e) {
			e.printStackTrace();
			logger.error("Error Occured in getHotelsByPlace",e);
			throw e;
		}
	}
}
