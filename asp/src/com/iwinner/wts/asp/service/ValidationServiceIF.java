package com.iwinner.wts.asp.service;

import java.util.Map;

import com.iwinner.wts.asp.exceptions.ServiceException;


public interface ValidationServiceIF {

	public Map<String,Integer> verifyAllServices(String username,String password)throws ServiceException;
}
