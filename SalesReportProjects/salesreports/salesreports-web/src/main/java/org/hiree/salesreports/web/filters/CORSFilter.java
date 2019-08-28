package org.hiree.salesreports.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import org.hiree.salesreports.cache.ConcurrentLruCache;
import org.hiree.salesreports.rest.config.interfaces.IContext;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.util.constant.HttpUtils;
import org.hiree.salesreports.web.rest.base.ContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CORSFilter extends OncePerRequestFilter {
	
	
	private static final String MDC_USER = "user";
	private static final int MAX_CACHE_SIZE = 1000;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * Session name to look for
	 */
	private String session;

	/**
	 * Reference context, for internal use only
	 */
	
	private IContext iContext;

	/**
	 * forward url (Required)
	 */
	private String url;
	private String[] urls;
	private ConcurrentLruCache<String, Boolean> urlMatch = new ConcurrentLruCache<String, Boolean>(MAX_CACHE_SIZE);

	/**
	 * Declares ignored URLs if any
	 */
	private String ignoreUrls;
	

	@Override
	protected final void initFilterBean() throws ServletException {
		WebApplicationContext ac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(super.getServletContext());
		initializeFilter(ac);
	}
	protected void initializeFilter(WebApplicationContext ac) throws ServletException {
		iContext = (IContext) ac.getBean("iContext");
		ContextHolder contextHolder = (ContextHolder) ac.getBean("contextHolder");
		/*userService = (UserService) ac.getBean("userService");
		ContextHolder contextHolder = (ContextHolder) ac.getBean("contextHolder");*/
		
		Assert.notNull(iContext, "iContext was not set!");
		Assert.notNull(contextHolder, "contextHolder was not set!");
	
		// Provide access to TimeoutFilter instance
		contextHolder.setcORSFilter(this);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");

		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			// response.addHeader("Access-Control-Allow-Headers",
			// "Authorization");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Max-Age", "1");
		}

		filterChain.doFilter(request, response);
	}
	
	public boolean populateSessionUser(HttpServletRequest request, HttpServletResponse response,
			 boolean userAddedtoMDC) throws IOException, ServletException {
		String requestURI = request.getRequestURI();

		// Validate against ignore urls property
		if (processRequestURI(requestURI)) {
			long start = System.currentTimeMillis();

			// Get session attribute
			Object o = WebUtils.getSessionAttribute(request, session);
			if (o == null) {
				logger.info("[doFilterInternal] Attribute '" + session + "' not found for session '"
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
		}

		return userAddedtoMDC;
	}

	/**
	 * default constructor defines mandatory filter parameters
	 */
	public CORSFilter() {
		addRequiredProperty("session");
		addRequiredProperty("url");
	}

	/*private String getHeaderValue(String headerName, HttpServletRequest request) {
		try {
			if (request != null) {
				return request.getHeader(headerName);
			}
		} catch (Exception e) {
			logger.error("getHeaderValue exception " + e.getMessage());
		}
		return null;
	}*/

	/**
	 * Validates request URI against ignoreUrls property, if matched session
	 * validation will not take place.
	 * 
	 * @param requestURI
	 * @return <b>boolean</b>
	 */
	private boolean processRequestURI(String requestURI) {
		if (urls == null) {
			return true;
		}

		// Return cached result, if found
		Boolean urlMatchResult = urlMatch.get(requestURI);
		if (urlMatchResult != null) {
			return urlMatchResult;
		}

		// See if there are more than one
		for (int i = 0; i < urls.length; i++) {
			if (StringUtils.contains(requestURI, urls[i])) {
				// Matched! Ignore this url
				return cacheAndReturn(requestURI, false);
			}
		}

		// Proceed
		return cacheAndReturn(requestURI, true);
	}

	/**
	 * Caches result and returns
	 * 
	 * @param requestURI
	 * @param result
	 * @return
	 */
	private boolean cacheAndReturn(String requestURI, boolean result) {
		urlMatch.put(requestURI, result);
		return result;
	}

	/**
	 * Sets up Ignore-urls
	 */
	protected void setupUrls() {
		urls = null;
		if (containsNonWhiteSpaceChar(ignoreUrls)) {
			urls = StringUtils.split(ignoreUrls, ",");
		}
		urlMatch.clear();
	}

	
	public  boolean containsNonWhiteSpaceChar(String str) {
		if (str==null || str.length()==0) {
			return false;
		}
		int strLen = str.length();
		for (int i=0; i<strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) return true;
		}
		return false;
	}
	/**
	 * Getter for the session property
	 * 
	 * @return the session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Setter for property session
	 * 
	 * @param session
	 *            the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * Getter for the url property
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for property url
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Setter for the property ignore URLs (comma delimited if multiple)
	 * 
	 * @param ignoreUrls
	 */
	public void setIgnoreUrls(String ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
		setupUrls();
	}

	public IContext getiContext() {
		return iContext;
	}

	public void setiContext(IContext iContext) {
		this.iContext = iContext;
	}

	

}
