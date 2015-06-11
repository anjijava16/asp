package com.iwinner.wts.asp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.service.AdminOperationServiceIF;

@Controller
public class NavigationController {

	private static Logger LOGGER=Logger.getLogger(NavigationController.class);
@Autowired
private AdminOperationServiceIF adminServiceIF;
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
}
