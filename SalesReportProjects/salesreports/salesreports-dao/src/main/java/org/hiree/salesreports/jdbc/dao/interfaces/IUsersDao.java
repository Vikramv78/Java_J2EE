package org.hiree.salesreports.jdbc.dao.interfaces;

import java.io.Serializable;

import org.hiree.salesreports.rest.dto.UserDTO;


public interface IUsersDao extends Serializable {
	UserDTO authorizeUser(String userName, String password) throws Exception;
	
	
}


