package com.iwinner.wts.asp.dao;

import com.iwinner.wts.asp.dto.AuditDTO;
import com.iwinner.wts.asp.exceptions.DaoException;

public interface StartUpDaoIF {
	public void insertAudit(AuditDTO auditDTO) throws DaoException;
}
