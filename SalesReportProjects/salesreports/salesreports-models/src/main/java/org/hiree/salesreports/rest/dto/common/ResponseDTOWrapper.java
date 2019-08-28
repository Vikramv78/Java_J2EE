/**
 * @(#)ResponseDTOWrapper.java
 * @author nmandavilli
 * @date Aug 07, 2017
 * 
 * $Id: ResponseDTOWrapper.java $
 * 
 * Copyright (c) 2017 Educational Testing Service. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Educational Testing Service.
 * ("Confidential Information").
 */
package org.hiree.salesreports.rest.dto.common;

import org.hiree.salesreports.rest.dto.interfaces.Payload;

/**
 * Response DTO Wrapper
 */
public class ResponseDTOWrapper {

	private ResponseDTO msg;
	private Payload payload;

	public ResponseDTO getMsg() {
		return msg;
	}

	public void setMsg(ResponseDTO msg) {
		this.msg = msg;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public ResponseDTOWrapper(ResponseDTO msg, Payload payload) {
		super();
		this.msg = msg;
		this.payload = payload;
	}
}
