package com.iwinner.wts.asp.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwinner.wts.asp.dao.AdminOperationDaoIF;
import com.iwinner.wts.asp.dto.CandidateDTO;
import com.iwinner.wts.asp.dto.GroupDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.CandidateVO;
import com.iwinner.wts.asp.form.GroupVO;
import com.iwinner.wts.asp.helper.DaoFactory;

@Service
public class AdminOperationServiceImpl implements AdminOperationServiceIF {

	private static Logger LOGGER = Logger.getLogger(AdminOperationServiceImpl.class);
	
	private AdminOperationDaoIF adminOperationDaoIF = null;

	public void addGroup(GroupVO groupVO) throws ServiceException {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroupName(groupVO.getGroupName());
		adminOperationDaoIF = DaoFactory.adminDaoFactory();
		try {
			adminOperationDaoIF.addGroup(groupDTO);
		} catch (DaoException e) {
			LOGGER.error("Error into the addGroup() " + e.getMessage());
			throw new ServiceException(e);
		}

	}

	public Map<Integer, String> groupDetailsService() throws ServiceException {
		adminOperationDaoIF = DaoFactory.adminDaoFactory();
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			List<GroupDTO> listGroupDTO = adminOperationDaoIF.groupDetails();
			for (GroupDTO groupDTO : listGroupDTO) {
				map.put(groupDTO.getGroupId(), groupDTO.getGroupName());
			}
		} catch (DaoException e) {
			LOGGER.error("Error into the addGroup() " + e.getMessage());
			throw new ServiceException(e);
		}
		return map;
	}

	public boolean userExistOrNot(String username) throws ServiceException {
		adminOperationDaoIF = DaoFactory.adminDaoFactory();
		boolean userExistOrNot = false;
		try {
			userExistOrNot = adminOperationDaoIF.userExistOrNot(username);
		} catch (DaoException e) {
			userExistOrNot = false;
			LOGGER.error("Error into the addGroup() " + e.getMessage());
			throw new ServiceException(e);
		}
		return userExistOrNot;
	}

	public Map<String, Integer> userInfo(String username)
			throws ServiceException {
		adminOperationDaoIF = DaoFactory.adminDaoFactory();
		Map<String, Integer> mapUser = new HashMap<String, Integer>();
		try {
			mapUser = adminOperationDaoIF.userInfo(username);
		} catch (DaoException e) {
			LOGGER.error("Error into the addGroup() " + e.getMessage());
			throw new ServiceException(e);
		}
		return mapUser;
	}

	public void addCandidte(CandidateVO canVO) throws ServiceException {
		adminOperationDaoIF = DaoFactory.adminDaoFactory();
		CandidateDTO candDTO = new CandidateDTO();
		candDTO.setEmpId(canVO.getEmpId());
		candDTO.setName(canVO.getName());
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroupId(canVO.getGroupVO().getGroupId());
		candDTO.setGroupDTO(groupDTO);
		try {
			adminOperationDaoIF.addCandidte(candDTO);
		} catch (DaoException e) {
			LOGGER.error("Error into the addCandidte() " + e.getMessage());
			throw new ServiceException(e);
		}
	}
	public List<CandidateVO> candidateDetails() throws ServiceException {
		List<CandidateDTO> candidateDTO = new ArrayList<CandidateDTO>();
		List<CandidateVO> candidateVO = new ArrayList<CandidateVO>();
		adminOperationDaoIF = DaoFactory.adminDaoFactory();
		try {
			candidateDTO = adminOperationDaoIF.candidateDetails();
			CandidateVO cVO = new CandidateVO();
			for (CandidateDTO cDTO : candidateDTO) {
				try {
					BeanUtils.copyProperties(cVO, cDTO);
					candidateVO.add(cVO);
				} catch (IllegalAccessException e) {
					LOGGER.error("Error into the candidateDetails() "+ e.getMessage());
					throw new ServiceException(e);
				} catch (InvocationTargetException e) {
					LOGGER.error("Error into the candidateDetails() "+ e.getMessage());
					throw new ServiceException(e);
				}
			}
		} catch (DaoException e) {
			LOGGER.error("Error into the candidateDetails() " + e.getMessage());
			throw new ServiceException(e);
		}
		return candidateVO;
	}
}
