package org.hiree.salesreports.services.interfaces;

import org.hiree.salesreports.rest.dto.UserDTO;

public interface IUserService {
	UserDTO authorizeUser(String userName, String password) throws Exception;
}
