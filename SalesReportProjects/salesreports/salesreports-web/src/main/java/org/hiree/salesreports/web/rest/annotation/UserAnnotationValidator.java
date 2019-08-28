package org.hiree.salesreports.web.rest.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hiree.salesreports.rest.config.interfaces.IContext;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.rest.dto.UserSession;
import org.hiree.salesreports.web.rest.base.ContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAnnotationValidator implements IBaseValidator {

	private static final Logger logger = LoggerFactory.getLogger(UserAnnotationValidator.class);
	@Autowired ContextHolder contextHolder;
	@Autowired IContext iContext;
	@Autowired UserSession userSession;
	//private static final String MDC_USER = "user";
	
	@Override
	public void validate(HttpServletRequest request, HttpServletResponse response, Method method,HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		//contextHolder.getcORSFilter().populateSessionUser(request, response, true);
		populateSessionUser(session);
	}
	protected void populateSessionUser(HttpSession session)
			throws Exception {

		try {

			UserSession user = userSession;
			UserSession user1 = (UserSession)session.getAttribute("userSession");
			if (logger.isDebugEnabled()) {
				//logger.debug("User session has been populated : {}", user.getLogin());
			}

			//iContext.setThreadUser(user);

		} catch (Exception e) {
			logger.error("Exception in getTimeoutFilter().doFilterInternal", e);
			throw e;
		}
	}
	
/*	protected void populateSessionUser(HttpServletRequest request, HttpServletResponse response, HttpSession session )
			throws Exception {

		try {
			// Calling TimeoutFilter explicitly, as it is not in filter chain for /Rest URL
			// pattern, to populate logged-in user from session into current thread
			//contextHolder.getcORSFilter().populateSessionUser(request, response, filterChain, false);
			String requestURI = request.getRequestURI();

			long start = System.currentTimeMillis();

			// Get session attribute
			//Object o = WebUtils.getSessionAttribute(request, "user");
			Object o =session.getAttribute("user");
			if (o == null) {
				logger.info("[doFilterInternal] Attribute '" + "user" + "' not found for session '"
						+ WebUtils.getSessionId(request) + "' (RequestURI: " + requestURI + ")");
				// This will echo out request IP address in logs
				HttpUtils.getRemoteIp(request);
			}

			if (o instanceof UserDTO) {
				UserDTO user = (UserDTO) o;
				if (logger.isDebugEnabled()) {
					logger.debug("User session has been populated : {}", user.getLogin());
				}

				if (!StringUtils.isBlank(user.getLogin())) {
					MDC.put(MDC_USER, user.getLogin() + ":" + request.getRemoteAddr());
					userAddedtoMDC = true;
				}

				// Set current thread user
				iContext.setThreadUser(user);

			} else {
				logger.warn("Invalid session attribute [{}]", o);
				throw new IllegalStateException("Invalid session attribute!");
			}

			// Log it only when needed
			if (logger.isDebugEnabled()) {
				logger.debug("Total time to process the request - {} ms", (System.currentTimeMillis() - start));
			}
		
		} catch (Exception e) {
			logger.error("Exception in getTimeoutFilter().doFilterInternal", e);
			throw e;
		}
	}*/
	
	public void clearThreadUser() {
		iContext.clearThreadUser();
	}
	
}
