package com.iwinner.wts.asp.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iwinner.wts.asp.dao.mapper.UserMapper;
import com.iwinner.wts.asp.dao.mapper.UserRowMapperCount;
import com.iwinner.wts.asp.dto.UserDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.helper.AspPortalConstants;

public class LDAPLoginDaoImpl implements LDAPLoginDaoIF {
	
	private static Logger LOGGER = Logger.getLogger(LDAPLoginDaoImpl.class);

	private static DataSource dataSource=null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public boolean verifyCreds(String username, String password)
			throws DaoException {
		LOGGER.info("$$$verifyCreds() $$$$");
		boolean verifyCreds=false;
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int count=jdbcTemplate.queryForInt(AspPortalConstants.SELECT_USER_VERIFY,new Object[]{username,password});
			if(count==1){
				verifyCreds=true;
			}else{
				verifyCreds=false;
			}
		} catch (Exception e) {
			LOGGER.error("Error into the verifyCreds(String username, String password) "+e.getMessage());
			e.printStackTrace();
			throw new DaoException();
		}
		return verifyCreds;
	}

	public UserDTO userInfo(String username) throws DaoException {
		UserDTO userDTO=new UserDTO();
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			userDTO=(UserDTO)jdbcTemplate.queryForObject(AspPortalConstants.SELECT_USER_DETAILS, new Object[]{username}, new UserMapper());
		} catch (Exception e) {
			LOGGER.error("Error into the  userInfo(String username)"+e.getMessage());
			e.printStackTrace();
			throw new DaoException();
		}
		return userDTO;
	}
	public void updatenNumberOfLoginTimes(String username)throws DaoException{
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int countIncrement=countLoginTimes(username);
			jdbcTemplate.update(AspPortalConstants.UPDATE_LOGIN_COUNT, new Object[]{countIncrement+AspPortalConstants.COUNT_PLUS,username});
		} catch (Exception e) {
			LOGGER.error("Error into the  updatenNumberOfLoginTimes(String username)"+e.getMessage());
			e.printStackTrace();
			throw new DaoException();
		}
		}
		public int countLoginTimes(String username)throws DaoException{
			Integer count=0;
			try {
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				 count=jdbcTemplate.queryForInt(AspPortalConstants.SELECT_LAST_HITS_COUNT,new Object[]{username});
			} catch (Exception e) {
				LOGGER.error("Error into the countLoginTimes(String username) "+e.getMessage());
				e.printStackTrace();
				throw new DaoException();
			}		
			return count;
		}
	
  public void updatLastLoginTime(String username)throws DaoException{
	  
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			Timestamp currentTimestamp=new Timestamp(new Date().getTime());
			Date currentDate=new Date();
			jdbcTemplate.update(AspPortalConstants.UPDATE_LAST_LOGIN_TIMES, new Object[]{currentTimestamp,currentDate,currentTimestamp,username});
		} catch (Exception e) {
			LOGGER.error("Error into the updatLastLoginTime(String username)"+e.getMessage());
			e.printStackTrace();
			throw new DaoException();
		}		
  }
  public Integer updateConsetiveSuccess(String username)throws DaoException{
	  Integer updateConstetiveSuccess=0;
			try {
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				Timestamp currentTimestamp=new Timestamp(new Date().getTime());
				Date currentDate=new Date();
                updateConstetiveSuccess=jdbcTemplate.update(AspPortalConstants.UPDATE_CONSERTIVE_SUCCESS, new Object[]{AspPortalConstants.COUNT_ZERO,
                		 currentTimestamp,currentDate,currentTimestamp,username});
			} catch (Exception e) {
				LOGGER.error("Error into the findConsetiveFailureCount(String username) "+e.getMessage());
				e.printStackTrace();
				throw new DaoException();
			}		
			return updateConstetiveSuccess;
  }
  public Integer updateConsectiveFailures(String username)throws DaoException{
	  int consetiveFailureCount=0;
	  try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			 consetiveFailureCount=findConsetiveFailureCount(username);
			if(consetiveFailureCount==0){
				jdbcTemplate.update(AspPortalConstants.UPDATE_CONSERTIVE_FAILUR, new Object[]{AspPortalConstants.COUNT_PLUS,username});	
				return findConsetiveFailureCount(username);
			}
			  if(consetiveFailureCount<3){
				jdbcTemplate.update(AspPortalConstants.UPDATE_CONSERTIVE_FAILUR, new Object[]{consetiveFailureCount+AspPortalConstants.COUNT_PLUS,username});
				consetiveFailureCount=findConsetiveFailureCount(username);
			  }else{
				  return consetiveFailureCount;
			  }
		} catch (Exception e) {
			LOGGER.error("Error into the  updateConsectiveFailures(String username)"+e.getMessage());
			e.printStackTrace();
			throw new DaoException();
		}
	  return consetiveFailureCount;
  }
  public Integer findConsetiveFailureCount(String username)throws DaoException{
	  Integer count=0;
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Integer> listCount=(List<Integer>)jdbcTemplate.query(AspPortalConstants.SELECT_CONSERTIVE_FAILURE_COUNT,new Object[]{username}, new UserRowMapperCount());
           for(Integer countV:listCount){
        	   count=countV;
           }
		} catch (Exception e) {
			LOGGER.error("Error into the findConsetiveFailureCount(String username) "+e.getMessage());
			e.printStackTrace();
			throw new DaoException();
		}		
		return count;
  }
  public void updateConsetiveFailureZero(String username)throws DaoException{
	  try {
		  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		  jdbcTemplate.update(AspPortalConstants.UPDATE_CONSERTIVE_FAILURE_ZERO, new Object[]{username});
	} catch (Exception e) {
		LOGGER.error("Error into the updateConsetiveFailureZero(String username) "+e.getMessage());
		e.printStackTrace();
		throw new DaoException();
	}
  }
  public Integer expireDateVerification(String username)throws DaoException{
	  Integer returnExpireDateValue=0;
	  try{
	  UserDTO userDTO=userInfo(username);
	  Date expireDate=userDTO.getExpirePasswordDate();
	  Date currentDate=new Date();
      if(currentDate.before(expireDate)){
    	  returnExpireDateValue=AspPortalConstants.DATE_CORRECT;
      }else if(currentDate.after(expireDate)){
    	  returnExpireDateValue=AspPortalConstants.DATE_EXPIRED;
      }else if(currentDate.equals(expireDate)) {
    	  returnExpireDateValue=AspPortalConstants.DATE_EQUAL;
      }
	  }catch(Exception e){
		  LOGGER.error("Error into the expireDateVerification()"+e.getMessage());
			e.printStackTrace();
			throw new DaoException();
	  }
	  return returnExpireDateValue;
  }
  
  public Integer userChecking(String username)throws DaoException{
	  Integer userChecking=0;
	  try {
		  UserDTO userDTO=userInfo(username);
		  String userStatus=userDTO.getAccountStatus();
		  if("ACTIVE".equals(userStatus)){
			  userChecking=AspPortalConstants.ACTIVE_STATUS;
		  }else if("INACTIVE".equals(userStatus)){
			  userChecking=AspPortalConstants.INACTIVE_STATUS;
		  }else if("DISABLE".equals(userStatus)){
			  userChecking=AspPortalConstants.DISABLE_STATUS;
		  }
	} catch (Exception e) {
		LOGGER.error("Error into the userChecking "+e.getMessage());
		e.printStackTrace();
		throw new DaoException();
	}
	  return userChecking;
  }
  public String userRole(String username)throws DaoException{
	  String userRole="";
	  try {
		  UserDTO userDTO=userInfo(username);
		  userRole=userDTO.getRole();
		  if("ADMIN".equals(userRole)){
			  userRole=AspPortalConstants.ADMIN_ROLE_ID.toString();
		  } else if("NORMAL".equals(userRole)){
			  userRole=AspPortalConstants.NORMA_USER_ID.toString();
		  }else if("DEV".equals(userRole)){
			  userRole=AspPortalConstants.HR_USER_ID.toString();
		  }else if("CUSTOMER".equals(userRole)){
			  userRole=AspPortalConstants.CUSTOMER_USER_ID.toString();
		  }
	} catch (Exception e) {
		LOGGER.error("Error into the userChecking "+e.getMessage());
		e.printStackTrace();
		throw new DaoException();
	}
	  return userRole;
  }
}
