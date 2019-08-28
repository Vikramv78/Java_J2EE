package org.hiree.salesreports.rest.dto;

import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.springframework.stereotype.Component;
@Component
public class ResponseBaseDTO<T> implements Payload{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9100910882217358391L;
	private String  status;
	private String  message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
