package com.iwinner.wts.asp.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.AuditForm;
import com.iwinner.wts.asp.helper.AspPortalConstants;
import com.iwinner.wts.asp.helper.IdGenerator;
import com.iwinner.wts.asp.service.StartUpServiceIF;

@Controller
public class StartUpController {

	private static Logger LOGGER = Logger.getLogger(StartUpController.class);
	@Autowired
	private StartUpServiceIF startUpserviceIF;

	@RequestMapping("startUp.action")
	public ModelAndView getStartUpService(HttpServletRequest request) {
		LOGGER.info("Enter Into the StartUpController() # getStartUpService()");
		String savedVistorInfo = "";
		Hashtable<String, String> headerInfo = new Hashtable<String, String>();
		Enumeration<String> headEnum = (Enumeration<String>) request.getHeaderNames();
		while (headEnum.hasMoreElements()) {
			String keyName = headEnum.nextElement();
			String valueName = request.getHeader(keyName);
			headerInfo.put(keyName, valueName);
		}
		InetAddress inetAdd = null;
		try {
			inetAdd = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			LOGGER.error("##### Error inside the  unknownHost " + e.getMessage());
		}
		String hostAndIpAddress[] = inetAdd.toString().split(AspPortalConstants.FORWARD_SLASH);
		String hostName = hostAndIpAddress[0];
		String ipAddress = hostAndIpAddress[1];
		String userAgent = headerInfo.get(AspPortalConstants.USER_AGENT);
		String browserName = "";
		if (userAgent != null && userAgent.contains(AspPortalConstants.CHROME_BROWSER)) {
			browserName = browserName + "Chrome";
		} else if (userAgent != null && userAgent.contains(AspPortalConstants.INTERNETEXPLORE_BROWSER)) {
			browserName = browserName + "MSIE";
		} else if (userAgent != null && userAgent.contains(AspPortalConstants.FIREFOX_BROWSER)) {
			browserName = browserName + "Firefox";
		} else if (userAgent != null) {
			browserName = browserName + "Not Chrome/MSIE/FireFox";
		}
		String osName = System.getProperty(AspPortalConstants.OS_NAME);
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp viewTime = new java.sql.Timestamp(date.getTime());

		AuditForm vForm = new AuditForm();
		vForm.setHostname(hostName);
		vForm.setIpAddress(ipAddress);
		vForm.setBrowser(browserName);
		vForm.setUserAgent(userAgent);
		vForm.setViewDate(date);
		vForm.setVistorranId(IdGenerator.getId());
		vForm.setViewDateAndTime(viewTime);
		vForm.setOsName(osName);
		vForm.setSystemUser(System.getProperty("user.name"));
		// AuditStartUpServiceIF
		// startUpServiceIF=ServiceFactory.getStartUpService();
		try {
			startUpserviceIF.insertAuditLog(vForm);
		} catch (ServiceException e) {
			LOGGER.error("##### Error Into the execute() " + e.getMessage());
		}

		return new ModelAndView("login");
	}
}
