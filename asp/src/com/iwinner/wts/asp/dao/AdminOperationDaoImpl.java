package com.iwinner.wts.asp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iwinner.wts.asp.dto.CandidateDTO;
import com.iwinner.wts.asp.dto.GroupDTO;
import com.iwinner.wts.asp.dto.UserASPDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.helper.AspPortalConstants;

public class AdminOperationDaoImpl implements AdminOperationDaoIF{
	
	private static Logger LOGGER = Logger.getLogger(LDAPLoginDaoImpl.class);

	private static DataSource dataSource=null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
public void addGroup(GroupDTO groupDTO) throws DaoException {
	LOGGER.info("##### addGroup is stared #####");
	try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(AspPortalConstants.INSERT_GROUP_QUERY,new Object[]{findGroupMaxValue()+AspPortalConstants.COUNT_PLUS,groupDTO.getGroupName()});
		
	}catch(Exception e){
		e.printStackTrace();
		LOGGER.error("Error into the addGroup "+e.getMessage());
		throw new DaoException();
	}
	LOGGER.info("##### addGroup is ended #####");
}
public Integer findGroupMaxValue()throws DaoException{
	Integer maxCount=null;
	try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		maxCount=jdbcTemplate.queryForInt(AspPortalConstants.FIND_MAX_GROUP_ID);
		if(maxCount==null){
			maxCount=0;
			return maxCount;
		}else{
			return maxCount;
		}
	}catch(Exception e){
		LOGGER.error("Error into the findGroupMaxValue() "+e.getMessage());
		throw new DaoException(e);
		
	}
}
public void addCandidte(CandidateDTO canDTO)throws DaoException{
	try {
		System.out.println("GroupID is AdmiNDaoImp class::>>>>>>>"+canDTO.getGroupDTO().getGroupId());
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(AspPortalConstants.INSERT_CANDIDATE_QUERY, new Object[]{findCandiateMaxValue()+AspPortalConstants.COUNT_PLUS,canDTO.getEmpId(),
				canDTO.getName(),userInfo(canDTO.getName()).get(canDTO.getName()),canDTO.getGroupDTO().getGroupId()});
	} catch (Exception e) {
		LOGGER.error("Error into the addCandidte() "+e.getMessage());
		throw new DaoException(e);
	}
}
public Integer findCandiateMaxValue()throws DaoException{
	Integer maxCount=null;
	try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		maxCount=jdbcTemplate.queryForInt(AspPortalConstants.FIND_MAX_CANDIDATE_ID);
		if(maxCount==null){
			maxCount=0;
			return maxCount;
		}else{
			return maxCount;
		}
	}catch(Exception e){
		LOGGER.error("Error into the findGroupMaxValue() "+e.getMessage());
		throw new DaoException(e);

	}
}
public List<GroupDTO> groupDetails()throws DaoException{
	
	List<GroupDTO> groupList=new ArrayList<GroupDTO>();
	try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		groupList=(List<GroupDTO>)jdbcTemplate.query(AspPortalConstants.SELECT_GROUPS_QUERY,new GroupDTOMapper());

	} catch (Exception e) {
		LOGGER.error("Error into the groupDetails() "+e.getMessage());
		throw new DaoException(e);
	}
	return groupList; 
}

public List<CandidateDTO>  candidateDetails()throws DaoException{
	List<CandidateDTO> listCandDTO=new ArrayList<CandidateDTO>();
	try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		listCandDTO=(List<CandidateDTO>)jdbcTemplate.query(AspPortalConstants.SELECT_GROUPS_QUERY,new CandidateRowMapper());

	} catch (Exception e) {
		LOGGER.error("Error into the groupDetails() "+e.getMessage());
		throw new DaoException(e);
	}
	
	return listCandDTO;
}
public UserASPDTO userDTO(String username)throws DaoException{
	UserASPDTO userDTO=new UserASPDTO();
	try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userDTO=(UserASPDTO)jdbcTemplate.queryForObject(AspPortalConstants.SELECT_USERS_QUERY, new Object[]{username}, new UserDTOMapper());
	} catch (Exception e) {
		LOGGER.error("Error into the groupDetails() "+e.getMessage());
		throw new DaoException(e);
	}
	return userDTO;
}
public Map<String,Integer> userInfo(String username)throws DaoException{
	Map<String,Integer> userDetails=new HashMap<String,Integer>();
	if(userExistOrNot(username)){
	UserASPDTO userDTO=userDTO(username);
	userDetails.put(userDTO.getUsername(),userDTO.getUserId());
	}
	return userDetails;
}

public boolean userExistOrNot(String username)throws DaoException{
	boolean userExist=false;
	try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count=jdbcTemplate.queryForInt(AspPortalConstants.SELECT_CHECK_USER,new Object[]{username});
		if(count>=1){
			userExist=true;
		}else{
			userExist=false;
		}
	} catch (Exception e) {
		userExist=false;
		LOGGER.error("Error into the groupDetails() "+e.getMessage());
	}
	return userExist;
}

/**
 ################## Inner class for GroupDetails information ############
 
 */
class GroupDTOMapper implements RowMapper<GroupDTO>{
	@Override
	public GroupDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		GroupDTO groupDTO=new GroupDTO();
		groupDTO.setGroupId(rs.getInt("GROUP_ID"));
		groupDTO.setGroupName(rs.getString("NAME"));
		return groupDTO;
	}
}
/**
################## Inner class for UserDetails information ############

*/
class UserDTOMapper implements RowMapper<UserASPDTO>{
	public UserASPDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		UserASPDTO userDTO=new UserASPDTO();
		userDTO.setUserId(rs.getInt("USER_ID"));
		userDTO.setUsername(rs.getString("USERNAME"));
		userDTO.setIpAddress(rs.getString("IPADDRESS"));
		userDTO.setAdmin(rs.getBoolean("IS_ADMIN"));
		return userDTO;
	}
}

/**
################## Inner class for CandidateRowMapper information ############

*/
class CandidateRowMapper implements RowMapper<CandidateDTO>{

	public CandidateDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		CandidateDTO candDTO=new CandidateDTO();
		candDTO.setName(rs.getString("NAME"));
		candDTO.setCandidateId(rs.getInt("CANDIDATE_ID"));
		candDTO.setEmpId(rs.getString("EMPLOYEE_ID"));
		GroupDTO gDTO=new GroupDTO();
		gDTO.setGroupId(rs.getInt("GROUP_ID"));
		candDTO.setGroupDTO(gDTO);
        UserASPDTO userDTO=new UserASPDTO();
        userDTO.setUserId(rs.getInt("USER_ID"));
        candDTO.setUserDTO(userDTO);
		return candDTO;
	}
}
}
