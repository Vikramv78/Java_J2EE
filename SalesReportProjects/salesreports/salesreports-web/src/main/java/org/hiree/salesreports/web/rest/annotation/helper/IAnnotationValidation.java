package org.hiree.salesreports.web.rest.annotation.helper;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface IAnnotationValidation {
	public void checkValidation(Method method, HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception ;
	public void checkAfterCompleteValidation(Method method, HttpServletRequest request,HttpServletResponse response, Exception ex) throws Exception;
}
