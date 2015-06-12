package com.iwinner.wts.asp.helper;

import com.iwinner.wts.asp.dao.AdminOperationDaoIF;
import com.iwinner.wts.asp.dao.AdminOperationDaoImpl;
import com.iwinner.wts.asp.dao.AssessmentDaoIF;
import com.iwinner.wts.asp.dao.AssessmentDaoImpl;
import com.iwinner.wts.asp.dao.LDAPLoginDaoIF;
import com.iwinner.wts.asp.dao.LDAPLoginDaoImpl;
import com.iwinner.wts.asp.dao.LoginDaoIF;
import com.iwinner.wts.asp.dao.LoginDaoImpl;
import com.iwinner.wts.asp.dao.StartUpDaoIF;
import com.iwinner.wts.asp.dao.StartUpDaoImpl;

public class DaoFactory {

	private static StartUpDaoIF startUpDaoIF = null;
	private static LDAPLoginDaoIF ldapDaoIF = null;
	private static LoginDaoIF loginDaoIF=null;
    private static AdminOperationDaoIF adminOpsDaoIF=null;
    private static AssessmentDaoIF assessmentDaoIF=null;
    
	static {
		adminOpsDaoIF=new AdminOperationDaoImpl();
		startUpDaoIF = new StartUpDaoImpl();
		ldapDaoIF=new LDAPLoginDaoImpl();
		loginDaoIF=new LoginDaoImpl();
		assessmentDaoIF=new AssessmentDaoImpl();
	}

	public static StartUpDaoIF startUpDaoFactory() {
		return startUpDaoIF;
	}
	public static LDAPLoginDaoIF ldapDaoFactory(){
		return ldapDaoIF;
	}
	public static LoginDaoIF loginDaoFactory(){
		return loginDaoIF;
	}
	public static AdminOperationDaoIF adminDaoFactory(){
		return adminOpsDaoIF;
	}
	public static AssessmentDaoIF assementDaoFactory(){
		return assessmentDaoIF;
	}
	
}
