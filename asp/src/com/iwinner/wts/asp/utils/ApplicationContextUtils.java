package com.iwinner.wts.asp.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwinner.wts.asp.dao.LDAPLoginDaoIF;
import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.helper.AspPortalConstants;
import com.iwinner.wts.asp.helper.PasswordEncoder;
import com.iwinner.wts.asp.service.ValidationServiceImpl;

public class ApplicationContextUtils {

	public static void main(String[] args) {
		System.out.println(PasswordEncoder.encodePassword("anjaiahspr"));
		/*ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		LDAPLoginDaoIF loginDao=(LDAPLoginDaoIF)context.getBean("loginDao");
		try {
			ValidationServiceImpl validationService=new ValidationServiceImpl();
			Map<String,Integer> validationMessage=validationService.verifyAllServices("anjaiahspr", "5f737e8517f2d4b5c6acd2d5ddcb554");
			System.out.println(validationMessage);
			List<Integer> listValues=new ArrayList<Integer>();
			Set set=validationMessage.entrySet();
			for(Iterator it=set.iterator();it.hasNext();){
				Map.Entry map=(Map.Entry)it.next();
				Integer value=(Integer)map.getValue();
				listValues.add(value);
             System.out.println(map.getKey());
			}

      for(Integer valuesIds:listValues){
    	  if(AspPortalConstants.LOGIN_SUCCESS.equals(valuesIds)){
    		  
    	  }
      }
		
		} catch (ServiceException e) {
			e.printStackTrace();
		}*/
	}
}
