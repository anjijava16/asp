package com.iwinner.wts.asp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iwinner.wts.asp.dto.ProblemsDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.helper.AspPortalConstants;

public class AssessmentDaoImpl implements AssessmentDaoIF {
	private static Logger LOGGER = Logger.getLogger(AssessmentDaoImpl.class);

	private static DataSource dataSource = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void saveProblem(ProblemsDTO problemDTO) throws DaoException {
		
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(
					AspPortalConstants.INSERT_PROBLEM_QUERY,
					new Object[] {
							problemDTO.getProblemId(),
							problemDTO.getProblemName(),
							problemDTO.getProblemDescription(),
							problemDTO.getProblemInput(),
							problemDTO.getProblemOutput(),
							problemDTO.getProblemTimeLine(),
							problemDTO.getProblemInsertDate(),
							problemDTO.getProblemInsertTimestamp() 
							});
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error into the saveProblem " + e.getMessage());
			throw new DaoException();
		}
	}
	public Integer findMaxiumProblemId()throws DaoException{
		Integer maxCount=null;
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			maxCount=jdbcTemplate.queryForInt(AspPortalConstants.SELECT_MAX_PROBLEM_ID);
			if(maxCount==null){
				maxCount=0;
				return maxCount;
			}else{
				return maxCount;
			}
		}catch(Exception e){
			LOGGER.error("Error into the findMaxiumProblemId() "+e.getMessage());
			throw new DaoException(e);
		}
	}
	
public boolean checkProblemExistOrNot(String problemName)throws DaoException{
	
	boolean checkProblem=false;
	
	try {
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		int count=jdbcTemplate.queryForInt(AspPortalConstants.FIND_PROBLEM_EXISTS_OR_NOT,new Object[]{problemName});
	    if(count==0){
	    	checkProblem=false;
	    }else{
	    	checkProblem=true;
	    }
	} catch (Exception e) {
		LOGGER.error("Error into the findMaxiumProblemId() "+e.getMessage());
		throw new DaoException(e);
	}
	return checkProblem;
}
public List<ProblemsDTO> listOfProblems()throws DaoException{
	
	List<ProblemsDTO> listOfProblems=new ArrayList<ProblemsDTO>();
      try {
    	  JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
    	  listOfProblems=(List<ProblemsDTO>)jdbcTemplate.queryForObject(AspPortalConstants.SELECT_PROBLEM_QUERY,new ProblemRowMaper());
	} catch (Exception e) {
		LOGGER.error("Error into the findMaxiumProblemId() "+e.getMessage());
		throw new DaoException(e);
	}
	return listOfProblems;
}
class ProblemRowMaper implements RowMapper<ProblemsDTO>{

	public ProblemsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		ProblemsDTO problemDTO=new ProblemsDTO();
		problemDTO.setProblemId(rs.getInt("PROBLEM_ID"));
		problemDTO.setProblemName(rs.getString("NAME"));
		problemDTO.setProblemDescription(rs.getString("DESCRIPTION"));
		problemDTO.setProblemInput(rs.getString("INPUT"));
		problemDTO.setProblemOutput(rs.getString("OUTPUT"));
		problemDTO.setProblemTimeLine(rs.getInt("TIMELIMIT"));
		problemDTO.setProblemInsertDate(rs.getDate("PROBLEM_INSERT_DATE"));
		problemDTO.setProblemInsertTimestamp(rs.getTimestamp("PROBLEM_INSERT_TIME"));
		return problemDTO;
	}
	
}
}
