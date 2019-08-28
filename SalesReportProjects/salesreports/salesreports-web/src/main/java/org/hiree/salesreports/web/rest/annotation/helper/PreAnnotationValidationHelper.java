/**
 * @(#)PreAnnotationValidationHelper.java
 * @author VVEERAVELU
 * @date Aug 03, 2017
 * 
 * $Id: PreAnnotationValidationHelper.java $
 * 
 * Copyright (c) 2017 Educational Testing Service. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Educational Testing
 * Service. ("Confidential Information").
 */
package org.hiree.salesreports.web.rest.annotation.helper;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hiree.salesreports.rest.annotation.User;
import org.hiree.salesreports.web.rest.annotation.UserAnnotationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Pre-Annotation Validation Helper
 * 
 * @author VVEERAVELU
 */
@Component("preAnnotationValidationHelper")
public class PreAnnotationValidationHelper implements IAnnotationValidation {

	@Autowired UserAnnotationValidator userAnnotationValidator;
	

	@Override
	public void checkValidation(Method method, HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception{

		

		if (method.isAnnotationPresent(User.class) || checkAnnotationInInterfaceMethods(method)) {
			userAnnotationValidator.validate(request, response, method,session);
		}
		
	}

	/**
	 * @param method
	 * @return
	 */
	private boolean  checkAnnotationInInterfaceMethods(Method method) {
		Class<?>[] clazz = method.getDeclaringClass().getInterfaces();
		if (null != clazz && clazz.length > 0) {
			Method[] interfaceMethod = clazz[0].getMethods();
			for(Method met :interfaceMethod){
				if(met.getName().contentEquals(method.getName())){
					if (met.isAnnotationPresent(User.class)){
						return true;
					}
					}
				}
		}
		
		return false;
	}

	@Override
	public void checkAfterCompleteValidation(Method method, HttpServletRequest request, HttpServletResponse response,
			Exception ex) throws ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
	}
	

}
