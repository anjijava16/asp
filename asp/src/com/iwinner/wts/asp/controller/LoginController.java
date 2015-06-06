package com.iwinner.wts.asp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.UserASPVO;
import com.iwinner.wts.asp.helper.AspPortalConstants;
import com.iwinner.wts.asp.helper.PasswordEncoder;
import com.iwinner.wts.asp.service.LoginServiceIF;
import com.iwinner.wts.asp.service.ValidationServiceIF;

@Controller
public class LoginController {

	@Autowired
	private ValidationServiceIF validateServiceIF;
	
	@Autowired
    private LoginServiceIF loginServiceIF;
	
	@RequestMapping(value = "loginVerification.action")
	public String loginVerification(HttpServletRequest request) {
		String target = "login";
        String message="";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username!=null || password!=null){
		List<Integer> listOfConditions=new ArrayList<Integer>();
		try {
			Map<String, Integer> loginVerficationDetails = validateServiceIF.verifyAllServices(username,PasswordEncoder.encodePassword(password));
			
			Set set = loginVerficationDetails.entrySet();
			for (Iterator it = set.iterator(); it.hasNext();) {
				Map.Entry mapEntry = (Map.Entry) it.next();
                Integer keyValue=(Integer)mapEntry.getValue();
                listOfConditions.add(keyValue);
			}
			
			if(listOfConditions.contains(AspPortalConstants.PASSWORD_DISABLE_TODAY)){
				request.setAttribute("message", "Your password is blocked try after 24 hours");
				return target;
			}
			if(listOfConditions.contains(AspPortalConstants.PASSWORD_BLOCKED)){
				request.setAttribute("message", "Your password is blocked try after 24 hours");
				return target;
			}
			
			if(listOfConditions.contains(AspPortalConstants.DATE_CORRECT)){
				 if(listOfConditions.contains(AspPortalConstants.ACTIVE_STATUS)){
            		 if(listOfConditions.contains(AspPortalConstants.LOGIN_SUCCESS)){
            			 request.getSession().setAttribute("username", username);
            			    if(listOfConditions.contains(AspPortalConstants.ADMIN_ROLE_ID)){
            			    	    UserASPVO userVO=new UserASPVO();
            			    		userVO=loginServiceIF.userDetails(username);
            			    		request.getSession().setAttribute("userVO",userVO);
            			    		target="adminHome";            			 	
            			    	}else{
            			    		target="testTakerHome";            			 
            			    	}
            		 }else{
            			 request.setAttribute("message", "Your not active user ,please contact (admin@iwinner.com)");
            			 return target;
            		 }
            	 }
			}else if(listOfConditions.contains(AspPortalConstants.DATE_EXPIRED)){
				 request.setAttribute("message", "Your password date is expired,please contact (admin@iwinner.com)");
    			 return target;
			}
			if(listOfConditions.contains(AspPortalConstants.LOGIN_FAILED)){
				request.setAttribute("message", "Invalid cred's,Please enter valid creds");
				return target;
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		}else{
			return target;
		}
		return target;
	}
}
