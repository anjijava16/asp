package com.iwinner.wts.asp.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iwinner.wts.asp.dto.AuditDTO;
import com.iwinner.wts.asp.exceptions.DaoException;
import com.iwinner.wts.asp.helper.AspPortalConstants;

public class StartUpDaoImpl implements StartUpDaoIF {
	private static Logger LOGGER = Logger.getLogger(StartUpDaoImpl.class);

	private static DataSource dataSource=null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insertAudit(AuditDTO auditDTO) throws DaoException {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         int x=jdbcTemplate.update(AspPortalConstants.INSERT_AUDIT_QUERY, new Object[]{auditDTO.getHostname(),auditDTO.getIpAddress(),auditDTO.getViewDate(),
        		   auditDTO.getViewDateAndTime(),auditDTO.getBrowser(),auditDTO.getSystemUser(),auditDTO.getUserAgent(),auditDTO.getVistorranId(),auditDTO.getOsName()
           });
         if(x==1){
        	 LOGGER.debug("insertion completed ;insertion value=["+x+"]");
         }
		} catch (Exception e) {
			LOGGER.error("insertion  failed " + e.getMessage());
			throw new DaoException(e);
		}
	}

}
