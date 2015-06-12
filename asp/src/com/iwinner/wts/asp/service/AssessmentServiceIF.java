package com.iwinner.wts.asp.service;

import java.util.List;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.ProblemsVO;

public interface AssessmentServiceIF {
	public void saveProblem(ProblemsVO problemVo) throws ServiceException;
	
	public boolean checkProblemExistOrNot(String problemName)throws ServiceException;
	
	public List<String> listOfProblems()throws ServiceException;
}
