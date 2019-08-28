package org.hiree.salesreports.web.rest.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface IBaseValidator {

	void validate(HttpServletRequest request,HttpServletResponse response,Method method,HttpSession session) throws Exception;

}
