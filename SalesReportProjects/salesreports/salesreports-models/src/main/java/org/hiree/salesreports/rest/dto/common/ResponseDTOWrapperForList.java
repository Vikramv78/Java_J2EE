/**
 * @(#)ResponseDTOWrapperForList.java
 * @author SVARMA001
 * @date Aug 10, 2017
 * 
 * $Id: ResponseDTOWrapperForList.java $
 * 
 * Copyright (c) 2017 Educational Testing Service. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Educational Testing Service.
 * ("Confidential Information").
 */
package org.hiree.salesreports.rest.dto.common;

import java.util.List;

import org.hiree.salesreports.rest.dto.interfaces.Payload;


/**
 * Response DTO Wrapper For List
 */
public class ResponseDTOWrapperForList {

	private ResponseDTO msg;
	private List<? extends Payload> payload;

	public ResponseDTO getMsg() {
		return msg;
	}

	public void setMsg(ResponseDTO msg) {
		this.msg = msg;
	}

	public List<? extends Payload> getPayload() {
		return payload;
	}

	public void setPayload(List<? extends Payload> payload) {
		this.payload = payload;
	}

	public ResponseDTOWrapperForList(ResponseDTO msg, List<? extends Payload> payload) {
		super();
		this.msg = msg;
		this.payload = payload;
	}
}