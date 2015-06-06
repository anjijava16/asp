package com.iwinner.wts.asp.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwinner.wts.asp.dao.LoginDaoIF;
import com.iwinner.wts.asp.dto.UserASPDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.UserASPVO;
import com.iwinner.wts.asp.helper.DaoFactory;

@Service
public class LoginServiceImpl implements LoginServiceIF {
	private static Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);
   private LoginDaoIF loginDaoIF=null;
	public UserASPVO userDetails(String username) throws ServiceException {
		UserASPVO userVO = new UserASPVO();
		loginDaoIF=DaoFactory.loginDaoFactory();
		try {
			UserASPDTO userASP=loginDaoIF.userDetails(username);
			try {
				BeanUtils.copyProperties(userVO, userASP);
			} catch (IllegalAccessException e) {
				LOGGER.error("Error into the userDetails BeanUtils issue "+e.getMessage());
			} catch (InvocationTargetException e) {
			}
		} catch (DaoException e) {
			LOGGER.error("Error into the userDetails BeanUtils issue "+e.getMessage());
		}
		return userVO;
	}
}
