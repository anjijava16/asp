package com.iwinner.wts.asp.dao;

import java.util.List;
import java.util.Map;

import com.iwinner.wts.asp.dto.CandidateDTO;
import com.iwinner.wts.asp.dto.GroupDTO;
import com.iwinner.wts.asp.exceptions.DaoException;

public interface AdminOperationDaoIF {
	public void addGroup(GroupDTO groupDTO) throws DaoException;

	public void addCandidte(CandidateDTO canDTO) throws DaoException;

	public List<GroupDTO> groupDetails() throws DaoException;

	public Map<String,Integer> userInfo(String username) throws DaoException;
	
	public boolean userExistOrNot(String username)throws DaoException ;
	
	public List<CandidateDTO>  candidateDetails()throws DaoException;
	
	public String grouName(Integer groupId)throws DaoException;
	
	public void update(String username,Integer groupId)throws DaoException;
	
	public Integer grouId(String groupName)throws DaoException;
}
