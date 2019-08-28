package org.hiree.salesreports.web.servlet;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;

public class Startup extends SpringServletBase implements Serializable{
	private static final long serialVersionUID = 1L;
	private static boolean inited = false;
	private static Logger logger = LoggerFactory.getLogger(Startup.class);
	
	public void init() {
		if (!inited) {
			logger.info("Calling Init method - Starting the app - performing all startup tasks");
			try {
			
				String warLocation = servletContext.getRealPath(StringUtils.EMPTY);
				logger.info(
						"------------------------------IBIS Services start up summary --------------------------------------");
				logger.info("WAR Location : {}", warLocation);
				
			} catch (Exception e) {
				logger.error("ERROR", e);
				System.exit(0);
			}
			inited = true;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	



}
