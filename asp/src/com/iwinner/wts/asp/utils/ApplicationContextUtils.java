package com.iwinner.wts.asp.utils;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwinner.wts.asp.dao.AdminOperationDaoImpl;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.helper.PasswordEncoder;

public class ApplicationContextUtils {

	public static void main(String[] args) {
		System.out.println(PasswordEncoder.encodePassword("anjaiahspr"));
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		AdminOperationDaoImpl adminDao=(AdminOperationDaoImpl)context.getBean("adminDao");
		try {
		/*	GroupDTO groupD=new GroupDTO();
			groupD.setGroupName("Telecom_Java");
			adminDao.addGroup(groupD); 
			      
		*/
			Map<Integer,String>map=adminDao.userInfo("anjaiahspr");
			System.out.println(map.size());
            Set set=map.entrySet();
            Iterator it=set.iterator();
            while(it.hasNext()){
            	Map.Entry mapEntry=(Map.Entry)it.next();
            	System.out.println(mapEntry.getKey()+"    "+mapEntry.getValue());
            }
			/*List<GroupDTO> listGrou=adminDao.groupDetails();
			 for(GroupDTO group:listGrou){
				 System.out.println(group.getGroupName()+"   "+group.getGroupId());
			 }*/
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*LDAPLoginDaoIF loginDao=(LDAPLoginDaoIF)context.getBean("loginDao");
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
