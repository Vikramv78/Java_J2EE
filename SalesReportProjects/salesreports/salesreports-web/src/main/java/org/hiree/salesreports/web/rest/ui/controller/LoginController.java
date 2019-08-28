package org.hiree.salesreports.web.rest.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hiree.salesreports.rest.dto.ResponseBaseDTO;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.rest.dto.UserSession;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authenticate")
@SessionAttributes("userSession")
public class LoginController extends AbstractRestController{
	
	@Autowired IUserService userService;

	@RequestMapping(path = "/login/{usename}/{password}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> login(@PathVariable String usename,
			@PathVariable String password,HttpServletRequest request,ModelMap map) throws Exception {
		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();

		UserDTO userDTO = userService.authorizeUser(usename, password);
		if (userDTO != null) {
			userDTO.setUserID(userDTO.getUserID());
			userDTO.setFullUsername(userDTO.getFname() + " " + userDTO.getLname());
			
			/*responseBaseDTO.setMessage("User Login Sucess ");
			responseBaseDTO.setStatus("Sucess");
		
			HttpSession oldsession = request.getSession(false);
			oldsession.addA
			if(oldsession != null) {
				oldsession.invalidate();
				oldsession = null;
			}*/
			
			request.getSession().setAttribute( "userSession", new UserSession(userDTO));		    
			//WebUtils.setSessionAttribute(request, "user", userDTO);
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
