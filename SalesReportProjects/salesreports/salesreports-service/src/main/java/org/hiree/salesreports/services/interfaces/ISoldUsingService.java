package org.hiree.salesreports.services.interfaces;

import java.util.List;

import org.hiree.salesreports.rest.dto.SoldUsingDTO;

public interface ISoldUsingService {
	public List<SoldUsingDTO> getAllSoldUsingByUserID(long userID) throws Exception;
}
