package com.jasvindersingh.airlinebookingsystem.services;

import com.jasvindersingh.airlinebookingsystem.exceptions.AppException;
import com.jasvindersingh.airlinebookingsystem.models.User;

public interface IUserService {
	User saveUser(User user) throws AppException;
	User authenticate(User user) throws AppException;
}
