package com.iwinner.wts.asp.service;

import java.util.List;
import java.util.Map;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.CandidateVO;
import com.iwinner.wts.asp.form.GroupVO;

public interface AdminOperationServiceIF {
	
	public void addGroup(GroupVO groupVO) throws ServiceException;

	public Map<Integer, String> groupDetailsService() throws ServiceException;
	
	public boolean userExistOrNot(String username)throws ServiceException;
	
	public Map<String,Integer> userInfo(String username) throws ServiceException;
	
	public void addCandidte(CandidateVO canVO)throws ServiceException;
	
	public List<CandidateVO>  candidateDetails()throws ServiceException;
	
	public String grouName(Integer groupId)throws ServiceException;
	
	public void associateGroupUpdate(String username,String groupName)throws ServiceException;
}
