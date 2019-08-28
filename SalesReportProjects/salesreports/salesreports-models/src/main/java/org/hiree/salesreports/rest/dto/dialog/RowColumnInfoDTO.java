package org.hiree.salesreports.rest.dto.dialog;

import org.hiree.salesreports.rest.dto.interfaces.Payload;

public class RowColumnInfoDTO implements Payload{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3792783232308253283L;
	private String fieldName;
	private String fieldValue;
	private String title;
	private Integer width;
	private Integer order;
	private String type;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	

}
