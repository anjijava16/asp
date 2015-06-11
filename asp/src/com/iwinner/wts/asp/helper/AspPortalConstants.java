package com.iwinner.wts.asp.helper;

import java.util.ResourceBundle;

public class AspPortalConstants {
	
	public static ResourceBundle CONFIG = ResourceBundle.getBundle("com.iwinner.wts.asp.utils.asp");
	
	public static String YYYYMMDD=CONFIG.getString("YYYYMMDD");
	public static String USER_AGENT = CONFIG.getString("USER_AGENT");
	public static String FORWARD_SLASH = CONFIG.getString("FORWARD_SLASH");
	public static String CHROME_BROWSER = CONFIG.getString("CHROME_BROWSER");
	public static String INTERNETEXPLORE_BROWSER = CONFIG.getString("INTERNET_EXPLORE_BROWSER");
	public static String FIREFOX_BROWSER = CONFIG.getString("FIREFOX_BROWSER");
	public static String OS_NAME = CONFIG.getString("OS_NAME");

	
	public static Integer DATE_CORRECT=1000;
	public static Integer DATE_EXPIRED=1001;
	public static Integer DATE_EQUAL=1002;

	public static Integer ACTIVE_STATUS=2000;
	public static Integer INACTIVE_STATUS=2001;
	public static Integer DISABLE_STATUS=2002;

	public static Integer LOGIN_SUCCESS=3000;
	public static Integer LOGIN_FAILED=30001;
	
	public static Integer PASSWORD_DISABLE_TODAY=4000;
	
	public static Integer ADMIN_ROLE_ID=5000;
	public static Integer NORMA_USER_ID=50001;
	public static Integer HR_USER_ID=5002;
	public static Integer CUSTOMER_USER_ID=5003;
	public static Integer PASSWORD_BLOCKED=5004;

	
	public static String USER_NOT_FOUND="User not found";
	public static String USER_FOUND="";
	//SQL QUERIES
	public static String INSERT_AUDIT_QUERY=CONFIG.getString("INSERT_QUERY");
	public static String SELECT_USER_VERIFY=CONFIG.getString("SELECT_USER_VERIFY");
	public static String SELECT_USER_DETAILS=CONFIG.getString("SELECT_USER_INFO");
	public static String SELECT_LAST_HITS_COUNT=CONFIG.getString("SELECT_COUNT_HITS");
	public static String UPDATE_LOGIN_COUNT=CONFIG.getString("UPDATE_LOGIN_COUNT");
	public static String UPDATE_LAST_LOGIN_TIMES=CONFIG.getString("UPDATE_LAST_LOGIN_TIMES");
	public static String UPDATE_CONSERTIVE_FAILUR=CONFIG.getString("UPDATE_CONSERTIVE_FAILUR");
	public static String SELECT_CONSERTIVE_FAILURE_COUNT=CONFIG.getString("SELECT_CONSERTIVE_FAILURE_COUNT");
	public static String UPDATE_CONSERTIVE_SUCCESS=CONFIG.getString("UPDATE_CONSERTIVE_SUCCESS");
	public static String UPDATE_CONSERTIVE_FAILURE_ZERO=CONFIG.getString("UPDATE_CONSERTIVE_FAILURE_ZERO");
	public static Integer COUNT_PLUS=1;
	public static Integer COUNT_ZERO=0;
	
	public static String SELECT_USERASP_QUERY=CONFIG.getString("SELECT_USERASP_QUERY");
	public static String FIND_MAX_GROUP_ID=CONFIG.getString("FIND_MAX_GROUP_ID");
	public static String INSERT_GROUP_QUERY=CONFIG.getString("INSERT_GROUP_QUERY");
	public static String FIND_MAX_CANDIDATE_ID=CONFIG.getString("FIND_MAX_CANDIDATE_ID");
	public static String INSERT_CANDIDATE_QUERY=CONFIG.getString("INSERT_CANDIDATE_QUERY");
	public static String SELECT_GROUPS_QUERY=CONFIG.getString("SELECT_GROUPS_QUERY");
	public static String SELECT_USERS_QUERY=CONFIG.getString("SELECT_USERS_QUERY");
	public static String SELECT_CHECK_USER=CONFIG.getString("SELECT_CHECK_USER");
}
