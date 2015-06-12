package com.iwinner.wts.asp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.CandidateVO;
import com.iwinner.wts.asp.form.GroupVO;
import com.iwinner.wts.asp.form.ProblemsVO;
import com.iwinner.wts.asp.helper.AspPortalConstants;
import com.iwinner.wts.asp.service.AdminOperationServiceIF;

@Controller
public class AdminController {

	@Autowired
	private AdminOperationServiceIF adminServiceImpl;
private static Logger LOGGER=Logger.getLogger(AdminController.class);	
@RequestMapping("CreateGroup.action")
public String creteForm(HttpServletRequest request){
	LOGGER.info("##### createForm method is stared #####");
	GroupVO groupVO=new GroupVO();
	groupVO.setGroupName(request.getParameter("groupName"));
	try {
		adminServiceImpl.addGroup(groupVO);
	} catch (ServiceException e) {
		e.printStackTrace();
		LOGGER.error("Error into the creteForm() "+e.getMessage());
		return "groupForm";
	}
	LOGGER.info("##### createForm method is ended #####");
	return "adminHome";
}

@RequestMapping("AddCandidateInsert.action")
public String candidateForm(HttpServletRequest request){
	GroupVO groupVO=new GroupVO();
	groupVO.setGroupId(Integer.parseInt(request.getParameter("groupId")));
	CandidateVO canVO=new CandidateVO();
	canVO.setEmpId(request.getParameter("employeeId"));
	canVO.setName(request.getParameter("username"));
    canVO.setGroupVO(groupVO);
    System.out.println("employeeId"+request.getParameter("employeeId")+"  "+"GroupID::>>>>"+Integer.parseInt(request.getParameter("groupId"))+"Username:::>>>>"+request.getParameter("username"));
    try {
		adminServiceImpl.addCandidte(canVO);
	} catch (ServiceException e) {
		LOGGER.error("Error into the candidateForm() "+e.getMessage());
		return "candidateForm";
	}
	return "adminHome";
}

@RequestMapping("userVerfication.action")
public void userVerficationChecking(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

	 boolean userExistOrNot=false;
	try {
		userExistOrNot = adminServiceImpl.userExistOrNot(request.getParameter("usename"));
		System.out.println("UserName is "+request.getParameter("usename")+"UserChecking::>>>"+userExistOrNot);
		 if(userExistOrNot==false){
			 response.getWriter().write(AspPortalConstants.USER_NOT_FOUND);
			 return ;
			 }else{
				 response.getWriter().write(AspPortalConstants.USER_FOUND);
				 return ;
			 }

	} catch (ServiceException e) {
		LOGGER.error("Error into the userVerficationChecking() "+e.getMessage());
	}
	 return ;

}
@RequestMapping("AssociateGroupWithCandidate.action")
public String associateGroupUpdate(HttpServletRequest request){
	try {
		adminServiceImpl.associateGroupUpdate(request.getParameter("candNameAndId"), request.getParameter("groupName"));
	} catch (ServiceException e) {
		LOGGER.error("Error into the associateGroupUpdate() "+e.getMessage());
		return "associateCandidateGroup";
	}
	return "adminHome";
}

}
