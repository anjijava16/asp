package com.iwinner.wts.asp.dao;

import com.iwinner.wts.asp.dto.UserASPDTO;
import com.iwinner.wts.asp.exceptions.DaoException;

public interface LoginDaoIF {

public UserASPDTO userDetails(String username)throws DaoException;	
}
