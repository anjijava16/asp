package com.iwinner.wts.asp.service;

import com.iwinner.wts.asp.exceptions.ServiceException;
import com.iwinner.wts.asp.form.AuditForm;

public interface StartUpServiceIF {

	public void insertAuditLog(AuditForm auditForm) throws ServiceException;
}
