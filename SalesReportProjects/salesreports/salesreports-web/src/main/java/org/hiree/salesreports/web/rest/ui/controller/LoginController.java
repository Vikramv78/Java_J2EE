package org.hiree.salesreports.web.rest.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hiree.salesreports.rest.config.interfaces.IContext;
import org.hiree.salesreports.rest.dto.ResponseBaseDTO;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.rest.dto.common.ResponseDTOWrapper;
import org.hiree.salesreports.services.interfaces.IUserService;
import org.hiree.salesreports.web.rest.ui.controller.base.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

@Controller
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authenticate")
//@SessionAttributes("userSession")
public class LoginController extends AbstractRestController{
	
	@Autowired IUserService userService;
	
	@Autowired IContext iContext;

	@RequestMapping(path = "/login/{usename}/{password}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	//@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> login(@PathVariable String usename,
			@PathVariable String password,HttpServletRequest request,ModelMap map,HttpServletResponse response) throws Exception {
		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();

		UserDTO userDTO = userService.authorizeUser(usename, password);
		if (userDTO != null) {
			userDTO.setUserID(userDTO.getUserID());
			userDTO.setFullUsername(userDTO.getFname() + " " + userDTO.getLname());
			userDTO.setLogin(userDTO.getFname());
			userDTO.setPassword(userDTO.getFname());
			HttpSession oldsession = request.getSession(false);
			
			if(oldsession != null) {
				oldsession.invalidate();
				oldsession = null;
			}
			
			HttpSession newsession = (HttpSession)request.getSession(true);
			
			//HttpServletRequest req = request.getR
			HttpServletRequest req = request;
			//req.getSession().setAttribute( "userSession", new UserSession(userDTO));
			req.getSession().setAttribute( "user", userDTO);
			req.getSession().setMaxInactiveInterval(1800);
			userDTO.setSessionId(newsession.getId());
		
			WebUtils.setSessionAttribute(req, "user", userDTO);
			iContext.setThreadUser(userDTO);
			return getResponse("User Login Sucess ", "Sucess", userDTO);
		} else {
			responseBaseDTO.setMessage("User Login Failed ");
			responseBaseDTO.setStatus("Failed");
			return getResponse("User Login Failed " , "Failed",responseBaseDTO);
		}

	}
	@RequestMapping(path = "/logout", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> logout(HttpSession sesssion) throws Exception {
		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();
		    sesssion.invalidate();
			responseBaseDTO.setMessage("User Logout Sucess ");
			responseBaseDTO.setStatus("Sucess");
			return getResponse(null, null, responseBaseDTO);
		

	}
	

}
