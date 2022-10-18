package com.jasvindersingh.airlinebookingsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.User;
import com.jasvindersingh.airlinebookingsystem.repositories.UserRepository;

@Service
public class UsersServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User saveUser(User user) throws AppException {
		User retUser = userRepo.save(user);
		return retUser;
	}
	
	public User authenticate(User user) throws AppException {
		User retUser = userRepo.findByEmail(user.getEmail());
		if(retUser != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if(passwordEncoder.matches(user.getPassword(),retUser.getPassword())) {
				return retUser;
			}
		}
		return null;
	}
	
	
}
