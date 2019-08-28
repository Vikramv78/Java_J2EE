package org.hiree.salesreports.rest.dto;

import org.hiree.salesreports.rest.dto.interfaces.Payload;

public class SoldUsingDTO implements Payload{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1437440967623019992L;
	/**
	 * 
	 */
	
	private long userId;
	private long soldUsingId;
	private String soldName;
	
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getSoldUsingId() {
		return soldUsingId;
	}
	public void setSoldUsingId(long soldUsingId) {
		this.soldUsingId = soldUsingId;
	}
	public String getSoldName() {
		return soldName;
	}
	public void setSoldName(String soldName) {
		this.soldName = soldName;
	}

}
