/**
 * @(#)HttpUtils.java
 * @author Rmerkushen
 * @date Aug 15, 2008
 * 
 * $Id: HttpUtils.java  $
 * 
 * Copyright (c) 2008 Educational Testing Service. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Educational Testing Service.
 * ("Confidential Information").
 */
package org.hiree.salesreports.util.constant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.HttpsURL;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;


public abstract class HttpUtils {
	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	
	public static final String TRUE_IP = "True-Client-IP";
	public static final String HEADER_X_FORWARDED_FOR = "X-FORWARDED-FOR";
	public static final String HEADER_X_FORWARDED_FOR_CAMEL = "X-Forwarded-For";
	/**
	 * Https prefix
	 */
	public static final String HTTPS_PREFIX = "https://";

	/**
	 * UTF-8 Encoding
	 */
	public static final String UTF_8 = "UTF-8";
	
	public static final String HTTPS_PROTOCOL = "https";

	/**
	 * Transfer encoding header
	 */
	public static final String TRANSFER_ENCODING = "Transfer-Encoding";

	/**
	 * Executes request
	 * 
	 * @param method
	 * @param uri
	 * @param httpConnectionManager
	 *            connection manager instance
	 * @throws IOException
	 */
	public static final void execute(HttpMethod method, URI uri, HttpConnectionManager httpConnectionManager) throws IOException {
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		if (httpConnectionManager != null)
			client.setHttpConnectionManager(httpConnectionManager);

		try {
			// set request uri
			method.setURI(uri);
			if (logger.isDebugEnabled())
				logger.debug("Requested method is [" + method + "]");

			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed status: " + method.getStatusLine());
			}
		} catch (URIException e) {
			logger.error("Invalid URI Exception: " + e.getMessage(), e);
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage(), e);
		}
	}

	/**
	 * Executes request and copies content to servlet response
	 * 
	 * @param method
	 * @param uri
	 * @throws IOException
	 */
	public static final void executeAndCopyContent(HttpMethod method, URI uri, HttpServletResponse response) throws IOException {
		SimpleHttpConnectionManager httpConnectionManager = null;
		try {
			// initialize connection manager, destroy connections
			httpConnectionManager = new SimpleHttpConnectionManager(true);

			// execute get
			execute(method, uri, httpConnectionManager);

			// copy status
			int statusCode = method.getStatusCode();
			if (logger.isDebugEnabled())
				logger.debug("Setting response code to [" + statusCode + "]");
			response.setStatus(statusCode);

			// get headers
			HttpUtils.copyHeaders(method, response);
			byte[] res = method.getResponseBody();
			// copy content
			response.setContentLength(res.length);
			int length = FileCopyUtils.copy(new ByteArrayInputStream(res), response.getOutputStream());

			// debug
			if (logger.isDebugEnabled())
				logger.debug("Bytes copied " + length);

		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage(), e);
		} finally {
			// Release the connection.
			method.releaseConnection();
			httpConnectionManager.shutdown();
		}
	}
	//TODO The code change should be accommodated in executeAndCopyContent for Dec 2011 release
	/**
	 * Executes request and copies content to servlet response from Pipeline previews. This is done to avoid Word 2007 issue which makes header call with the Microsoft Office Existence Discovery Protocol
	 * The method is duplicated from executeAndCopyContent due time constraint for testing. This is added for Production 5.6 break fix in September 2011.
	 * @param method
	 * @param uri
	 * @throws IOException
	 */
	public static final void executeAndCopyContentForPipelinePreviews(HttpMethod method, URI uri, HttpServletResponse response) throws IOException {
		SimpleHttpConnectionManager httpConnectionManager = null;
		try {
			// initialize connection manager, destroy connections
			httpConnectionManager = new SimpleHttpConnectionManager(true);

			// execute get
			execute(method, uri, httpConnectionManager);
			// copy status
			int statusCode = method.getStatusCode();
			if (logger.isDebugEnabled())
				logger.debug("Setting response code to [" + statusCode + "]");
			response.setStatus(statusCode);

			
			// get headers
			HttpUtils.copyHeaders(method, response);
			byte[] res = method.getResponseBody();
			// This is done to avoid word 2007 issue which makes header call with the Microsoft Office Existence Discovery Protocol
			if( StringUtils.equalsIgnoreCase(response.getContentType(), "application/msword")   ){
				response.setHeader("Content-Disposition","Attachment");
			}
			// copy content
			response.setContentLength(res.length);
			int length = FileCopyUtils.copy(new ByteArrayInputStream(res), response.getOutputStream());
			
			// debug
			if (logger.isDebugEnabled())
				logger.debug("Bytes copied " + length);

		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage(), e);
		} finally {
			// Release the connection.
			method.releaseConnection();
			httpConnectionManager.shutdown();
		}
	}

	/**
	 * Copy headers from request to new request method
	 * 
	 * @param from
	 * @param to
	 */
	public static final void copyHeaders(HttpServletRequest from, HttpMethod to) {
		// copy headers
		Enumeration<?> headers = from.getHeaderNames();
		while (headers.hasMoreElements()) {
			String name = (String) headers.nextElement();
			String header = from.getHeader(name);
			if (logger.isDebugEnabled())
				logger.debug("Adding request header [" + name + "] with value [" + header + "]");
			to.addRequestHeader(name, header);
		}
	}

	/**
	 * Copy headers from method to response
	 * 
	 * @param from
	 * @param to
	 */
	public static final void copyHeaders(HttpMethod from, HttpServletResponse to) {
		// get headers
		Header[] responseHeaders = from.getResponseHeaders();
		for (int i = 0; i < responseHeaders.length; i++) {
			if (logger.isDebugEnabled())
				//logger.debug(IBISGenericUtil.sanitizeString("Writing response header [" + responseHeaders[i].getName() + "] value [" + responseHeaders[i].getValue() + "]", "UTF-8"));
			if (!StringUtils.equals(responseHeaders[i].getName(), TRANSFER_ENCODING))
				to.addHeader(responseHeaders[i].getName(), responseHeaders[i].getValue());
		}
	}

	/**
	 * Builds URI
	 * 
	 * @param url
	 * @return
	 */
	public static final URI uri(String url) throws URIException {
		Assert.notNull(url, "Url cannot be null!");
		char[] urlArray = new char[url.length()];
		for (int i = 0; i < url.length(); i++)
			urlArray[i] = url.charAt(i);

		if (logger.isDebugEnabled())
			logger.debug("Building URL [" + url + "]");
		if (url.startsWith(HTTPS_PREFIX))
			return new HttpsURL(urlArray);
		else
			return new HttpURL(urlArray);
	}

	/**
	 * builds appropriate url
	 * 
	 * @param request
	 * @return
	 */
	public static final String buildServerUrl(HttpServletRequest request) {
		StringBuilder url = new StringBuilder();
		String protocol = request.getScheme();
		
			
		/*if (IBISUtil.isSkipSubDomainReset())
			url.append(protocol);
		else*/
			url.append(HTTPS_PROTOCOL);

		url.append("://");
		url.append(request.getServerName());
		
		//logger.info("Server Name  ----"+ request.getServerName());
		//logger.info("Server Port  ----"+ request.getServerPort());
		
		if (request.getServerPort() != 80 && request.getServerPort() != 443)
			url.append(":" + request.getServerPort());
		
		logger.info("User domain url: " + url.toString());
		return url.toString();
	}
	
	/**
	 * @param zis
	 * @return
	 */
	public static byte[] getContent(ZipInputStream zis) throws Exception
	{
		byte[] bytes = null;
		byte buffer[] = new byte[1024];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = 0;
		while ((len = zis.read(buffer, 0, 1024)) > -1)
		{
			out.write(buffer, 0, len);
		}
		bytes = out.toByteArray();
		out.flush();
		out.close();
		return bytes;
	}
	
	/**
	 * @param zis
	 * @return
	 */
	public static byte[] getContent(InputStream is) throws Exception
	{
		byte[] bytes = null;
		byte buffer[] = new byte[1024];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = 0;
		while ((len = is.read(buffer, 0, 1024)) > -1)
		{
			out.write(buffer, 0, len);
		}
		bytes = out.toByteArray();
		out.flush();
		out.close();
		return bytes;
	}
	
	/**
	 * Returns the client remote ip address
	 * @param req
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest req) {
		String remoteIP = req.getHeader(TRUE_IP);
		logger.info("getRemoteIp(): IP security check: IP Address from ("+TRUE_IP+") - " + remoteIP);
		
		if (remoteIP == null) {
			remoteIP = req.getHeader(HEADER_X_FORWARDED_FOR);
			logger.info("getRemoteIp(): IP security check: remote IP value returned by " + HEADER_X_FORWARDED_FOR + " is - " + remoteIP);
			if (remoteIP == null) {
				remoteIP = req.getHeader((HEADER_X_FORWARDED_FOR).toLowerCase());
				logger.info("getRemoteIp(): IP security check: remote IP value returned by " + (HEADER_X_FORWARDED_FOR).toLowerCase() + " is - " + remoteIP);
				
				if (remoteIP == null) {
					remoteIP = req.getHeader(HEADER_X_FORWARDED_FOR_CAMEL);
					logger.info("getRemoteIp(): IP security check: remote IP value returned by "+HEADER_X_FORWARDED_FOR_CAMEL+" = " + remoteIP);
					
					if (remoteIP == null) {
						remoteIP = req.getRemoteAddr();
						logger.info("getRemoteIp(): IP security check: remote IP value returned by req.getRemoteAddr() is - " + remoteIP);
					}
				}
			}
		}
		return remoteIP;
	}
	
	/**
	 * This method returns request URL
	 * 
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}
}
