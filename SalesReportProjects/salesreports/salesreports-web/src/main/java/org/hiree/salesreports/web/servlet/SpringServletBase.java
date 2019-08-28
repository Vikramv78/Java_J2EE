package org.hiree.salesreports.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class SpringServletBase  implements HttpRequestHandler {
	private static Logger logger = LoggerFactory.getLogger(SpringServletBase.class);
	@Autowired
	protected ServletContext servletContext;
	
		 
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			if (request.getMethod().equalsIgnoreCase("GET")) {
				doGet(request,response);
			} else if (request.getMethod().equalsIgnoreCase("POST")) {
				doPost(request,response);
			}
		} catch (Exception e) {
			logger.error("Error with session diagnostics ", e);
			PrintWriter out = response.getWriter();
			out.println("Error");
			out.flush();
			out.close();
		}
	}
	
	protected WebApplicationContext webApplicationContext;

	protected Object getSpringBean(String beanName) {
		if (webApplicationContext == null){
			webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		}
		return webApplicationContext.getBean(beanName);
	}

	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception;
	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

