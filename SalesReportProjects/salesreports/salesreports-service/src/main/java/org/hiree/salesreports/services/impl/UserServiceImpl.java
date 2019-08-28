package org.hiree.salesreports.services.impl;

import org.hiree.salesreports.jdbc.dao.interfaces.IUsersDao;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUsersDao  usersDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDTO authorizeUser(String userName, String password) throws Exception {
		
		return usersDao.authorizeUser(userName, password);
	}

}
