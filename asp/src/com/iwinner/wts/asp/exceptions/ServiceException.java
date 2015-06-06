package com.iwinner.wts.asp.exceptions;

/***
 * It is ServiceException custom exception
 * 
 * @author anjaiahspr@iWinner.com
 * @since 19thMay,2015
 * @version 1.0
 * 
 */
public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
