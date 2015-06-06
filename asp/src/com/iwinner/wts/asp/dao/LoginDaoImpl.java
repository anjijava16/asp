package com.iwinner.wts.asp.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iwinner.wts.asp.dao.mapper.UserASPMapper;
import com.iwinner.wts.asp.dto.UserASPDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.helper.AspPortalConstants;

public class LoginDaoImpl implements LoginDaoIF{
	private static Logger LOGGER = Logger.getLogger(StartUpDaoImpl.class);

	private static DataSource dataSource=null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public UserASPDTO userDetails(String username) throws DaoException {
		LOGGER.info("$$$userDetails() $$$$+:::username=["+username+"]");
		UserASPDTO userDTO=new UserASPDTO();
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			userDTO=(UserASPDTO)jdbcTemplate.queryForObject(AspPortalConstants.SELECT_USERASP_QUERY, new Object[]{username}, new UserASPMapper());
		} catch (Exception e) {
			LOGGER.error("Error into the userDetails(String username) "+e.getMessage());
		}
		return userDTO;
	}
}
