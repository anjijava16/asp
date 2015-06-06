package com.iwinner.wts.asp.service;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.UserASPVO;

public interface LoginServiceIF {
	public UserASPVO userDetails(String username)throws ServiceException;	
}
