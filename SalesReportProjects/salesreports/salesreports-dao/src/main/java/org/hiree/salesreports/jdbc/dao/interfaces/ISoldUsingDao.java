package org.hiree.salesreports.jdbc.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hiree.salesreports.rest.dto.SoldUsingDTO;


public interface ISoldUsingDao extends Serializable {
	
	List<SoldUsingDTO> getAllSoldUsingByUserID(long userID) throws Exception ;
	int getSoldUsingByUserAndNameCount(SoldUsingDTO soldUsingVo) throws Exception ;
	void saveSoldUsing(SoldUsingDTO soldUsingVo,String userName) throws Exception;
	List<SoldUsingDTO> getAllSoldUsingAllUsers() throws Exception ;
	List<Long> getSoldUsingByNames(String names)  throws Exception ;
	
}
