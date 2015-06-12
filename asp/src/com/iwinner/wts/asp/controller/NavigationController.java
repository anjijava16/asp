package com.iwinner.wts.asp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.CandidateVO;
import com.iwinner.wts.asp.service.AdminOperationServiceIF;
import com.iwinner.wts.asp.service.AssessmentServiceIF;

@Controller
public class NavigationController {

	private static Logger LOGGER=Logger.getLogger(NavigationController.class);
@Autowired
private AdminOperationServiceIF adminServiceIF;

@Autowired
private AssessmentServiceIF assessmentServiceIF;

@RequestMapping("CreateGroupPage.action")
public String requestCreateGroupForm(){
	return "groupForm";
}
@RequestMapping("AdminHomePage.action")
public String forwardHomePage(){
	return "adminHome";
}
@RequestMapping("AddCandidate.action")
public String forwardCandidateFormPage(HttpServletRequest request){

	try {
		Map<Integer,String> groupDetails=adminServiceIF.groupDetailsService();
		request.getSession().setAttribute("mapGroup", groupDetails);
	} catch (ServiceException e) {
		LOGGER.error("Error Into the forwardCandidateFormPage() page  "+e.getMessage());
	}
	return "candidateForm";
}
@RequestMapping("assignGroup.action")
public String assignGroup(HttpServletRequest request)throws ServletException,IOException{
	List<CandidateVO> listCandi=new ArrayList<CandidateVO>();
	try {
		listCandi=adminServiceIF.candidateDetails();
	} catch (ServiceException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Map<String,String> mapCandidates=new HashMap<String, String>();
	for(CandidateVO canVO:listCandi){
		try {
			mapCandidates.put(canVO.getName()+"-"+canVO.getEmpId(), adminServiceIF.grouName(canVO.getGroupVO().getGroupId()));
		} catch (ServiceException e) {
			LOGGER.error("Error Into the forwardCandidateFormPage() page  "+e.getMessage());
			return "";
		}
	}
	request.getSession().setAttribute("assignGroupList", mapCandidates);
	return "associateCandidateGroup";
}
@RequestMapping("addProblemPage.action")
public String viewAddAssement(){
	return "addproblem";
}

@RequestMapping("testCase.action")
public String testCasePage(HttpServletRequest request){
	List<String> listOfProblems=new ArrayList<String>();
	try {
		listOfProblems=assessmentServiceIF.listOfProblems();
	} catch (ServiceException e) {
		LOGGER.error("Error Into the forwardCandidateFormPage() page  "+e.getMessage());
	}
	request.getSession().setAttribute("listOfProblems", listOfProblems);
	return "addtestcase";
}
}
