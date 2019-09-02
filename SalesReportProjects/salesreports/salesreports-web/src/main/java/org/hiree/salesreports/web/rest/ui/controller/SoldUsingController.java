package org.hiree.salesreports.web.rest.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hiree.salesreports.rest.annotation.User;
import org.hiree.salesreports.rest.dto.SoldUsingDTO;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.rest.dto.common.ResponseDTOWrapperForList;
import org.hiree.salesreports.services.interfaces.ISoldUsingService;
import org.hiree.salesreports.web.rest.ui.controller.base.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/soldusing")
public class SoldUsingController extends AbstractRestController{
	
	@Autowired 	ISoldUsingService soldUsingService;
	
	@User
	@RequestMapping(path = "/getsoldusing", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	//@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapperForList>>  getSoldUsing(HttpServletRequest request) throws Exception {

		//TODO Hard code user id
		UserDTO user = getUser();
		//long userID =1;
		List<SoldUsingDTO> soldUsingDTOList= soldUsingService.getAllSoldUsingByUserID(user.getUserID());
		return getResponse(soldUsingDTOList);
	}

}
