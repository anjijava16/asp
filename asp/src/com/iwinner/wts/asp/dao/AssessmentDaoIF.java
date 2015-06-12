package com.iwinner.wts.asp.dao;

import java.util.List;

import com.iwinner.wts.asp.dto.ProblemsDTO;
import com.iwinner.wts.asp.exceptions.DaoException;

public interface AssessmentDaoIF {
	public void saveProblem(ProblemsDTO problemDTO)throws DaoException;
	
	public Integer findMaxiumProblemId()throws DaoException;
	
	public boolean checkProblemExistOrNot(String problemName)throws DaoException;
	
	public List<ProblemsDTO> listOfProblems()throws DaoException;
}
