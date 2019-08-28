package org.hiree.salesreports.services.impl;

import java.util.List;

import org.hiree.salesreports.jdbc.dao.interfaces.ISoldUsingDao;
import org.hiree.salesreports.rest.dto.SoldUsingDTO;
import org.hiree.salesreports.services.interfaces.ISoldUsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("soldUsingService")
public class SoldUsingServiceImpl implements ISoldUsingService{
	
	@Autowired
	private ISoldUsingDao soldUsingDao;

	@Override
	public	List<SoldUsingDTO> getAllSoldUsingByUserID(long userID) throws Exception{
		return soldUsingDao.getAllSoldUsingByUserID(userID);
	}
	
}
