package com.iwinner.wts.asp.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwinner.wts.asp.dao.AssessmentDaoIF;
import com.iwinner.wts.asp.dto.ProblemsDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.ProblemsVO;
import com.iwinner.wts.asp.helper.AspPortalConstants;
import com.iwinner.wts.asp.helper.DaoFactory;

@Service
public class AssessmentServiceImpl implements AssessmentServiceIF {
	private static Logger LOGGER=Logger.getLogger(AssessmentServiceImpl.class);
   private AssessmentDaoIF assessmentDaoIF=null;
	public void saveProblem(ProblemsVO problemVo) throws ServiceException {
		assessmentDaoIF=DaoFactory.assementDaoFactory();
		ProblemsDTO problemDTO=new ProblemsDTO();
		try {
			problemVo.setProblemId(assessmentDaoIF.findMaxiumProblemId()+AspPortalConstants.COUNT_PLUS);
    		problemVo.setProblemInsertDate(new Date());
	    	problemVo.setProblemInsertTimestamp(new Timestamp(new Date().getTime()));
			BeanUtils.copyProperties(problemDTO, problemVo);
			assessmentDaoIF.saveProblem(problemDTO);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error into the saveProblem() "+ e.getMessage());
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error into the saveProblem() "+ e.getMessage());
			throw new ServiceException(e);
		} catch (DaoException e) {
			LOGGER.error("Error into the saveProblem() "+ e.getMessage());
			throw new ServiceException(e);
		}
}
	public boolean checkProblemExistOrNot(String problemName)throws ServiceException{
		assessmentDaoIF=DaoFactory.assementDaoFactory();
		boolean problemExistOrNot=false;
		try {
			problemExistOrNot= assessmentDaoIF.checkProblemExistOrNot(problemName);
		} catch (DaoException e) {
			LOGGER.error("Error into the saveProblem() "+ e.getMessage());
			problemExistOrNot=false;
			throw new ServiceException(e);
		}
		
		return problemExistOrNot;
	}
	
	public List<String> listOfProblems() throws ServiceException {
		assessmentDaoIF=DaoFactory.assementDaoFactory();
		List<String> listOfProblems=new ArrayList<String>();
		try {
			List<ProblemsDTO> listOrProblemsDTO=assessmentDaoIF.listOfProblems();
			for(ProblemsDTO problemDTO:listOrProblemsDTO){
				listOfProblems.add(problemDTO.getProblemName());
			}
		} catch (DaoException e) {
			LOGGER.error("Error into the listOfProblems() "+ e.getMessage());
			throw new ServiceException(e);		}
		return listOfProblems;
	}
}
