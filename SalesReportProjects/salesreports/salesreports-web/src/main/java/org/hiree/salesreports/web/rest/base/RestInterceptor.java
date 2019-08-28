/**
 * @(#)RestInterceptor.java
 * @author VVEERAVELU
 * @date May 18, 2017
 * 
 * $Id: RestInterceptor.java $
 * 
 * Copyright (c) 2017 Educational Testing Service. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Educational Testing
 * Service. ("Confidential Information").
 */
package org.hiree.salesreports.web.rest.base;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hiree.salesreports.web.rest.annotation.UserAnnotationValidator;
import org.hiree.salesreports.web.rest.annotation.helper.PreAnnotationValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Rest Interceptor
 * 
 * @author VVEERAVELU
 */
@Component
public class RestInterceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(RestInterceptor.class);


	@Autowired PreAnnotationValidationHelper preAnnotationValidationHelper;
	@Autowired UserAnnotationValidator userAnnotationValidator;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod hm = (HandlerMethod) handler;
		Method method = hm.getMethod();
		preAnnotationValidationHelper.checkValidation(method, request, response,request.getSession());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod hm = (HandlerMethod) handler;
		//Method method = hm.getMethod();
		//postAnnotationValidationHelper.checkValidation(method, request, response);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerMethod hm = (HandlerMethod) handler;
		//Method method = hm.getMethod();
		
		//completionAnnotationValidationHelper.checkAfterCompleteValidation(method, request, response,ex);
		// This should be the last statement in this method
		userAnnotationValidator.clearThreadUser();
	}
}
