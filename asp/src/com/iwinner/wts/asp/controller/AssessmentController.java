package com.iwinner.wts.asp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.ProblemsVO;
import com.iwinner.wts.asp.helper.AspPortalConstants;
import com.iwinner.wts.asp.service.AssessmentServiceIF;

@Controller
public class AssessmentController {

	@Autowired
	private AssessmentServiceIF assessmentServiceIF;

	@RequestMapping("addProblemIn.action")
	public String addProblem(HttpServletRequest request) {
		ProblemsVO probVO = new ProblemsVO();
		String problemName=request.getParameter("problemName");
		try {
			if(!assessmentServiceIF.checkProblemExistOrNot(problemName)){
				probVO.setProblemName(problemName);
				probVO.setProblemDescription(request.getParameter("description"));
				probVO.setProblemInput(request.getParameter("inputData"));
				probVO.setProblemOutput(request.getParameter("outputData"));
				probVO.setProblemTimeLine(Integer.parseInt(request.getParameter("timeLimit")));
				assessmentServiceIF.saveProblem(probVO);
			}else{
				request.setAttribute("problemExists", AspPortalConstants.PROBLEM_FOUND);
				 return "redirect:/addProblemPage.action";
			}
		} catch (ServiceException e1) {
			return "addproblem";
		}
		return "adminHome";
	}
	@RequestMapping("problemChecking.action")
	public void problemChecking(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String problemName=request.getParameter("problemName");
		try {
			boolean problemChecking=assessmentServiceIF.checkProblemExistOrNot(problemName);
			if(problemChecking){
				 response.getWriter().write(AspPortalConstants.PROBLEM_FOUND);
				 return ;
			}else{
				 response.getWriter().write(AspPortalConstants.PROBLEM_NOT_FOUND);
				 return ;
			}
		} catch (ServiceException e) {
		}
	}
	@RequestMapping("AddTestCase.action")
	public String addTestCase(HttpServletRequest request)throws ServletException,IOException{
		
		
		return "";
	}
}
