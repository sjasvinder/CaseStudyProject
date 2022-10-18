package com.jasvindersingh.airlinebookingsystem.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends Exception {
	
	private static final Logger logger = LoggerFactory.getLogger(AppException.class);
	
	public AppException(Throwable e) {
		super(e);
		logger.error(e.getLocalizedMessage());
	}
}
