package com.iwinner.wts.asp.exceptions;

/**
 * 
 * It is DaoException customException
 * 
 * @author anjaiahspr@iWinner.com
 * @since 19thMay,2015
 * @version 1.0
 * 
 */

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
