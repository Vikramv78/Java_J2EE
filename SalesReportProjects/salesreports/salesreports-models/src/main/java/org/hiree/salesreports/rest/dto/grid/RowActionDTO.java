/**
* @(#) RowActionDTO.java
* @author AGANDHAM
* @date Dec 11, 2017
*
* 
* Copyright (c) 2006 Educational Testing Service. All Rights Reserved.
*
* This software is the confidential and proprietary information of Educational
* Testing Service. ("Confidential Information").
*/

package org.hiree.salesreports.rest.dto.grid;

import java.util.ArrayList;
import java.util.List;

import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.hiree.salesreports.util.constant.C;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * This class is used to define any row actions that need to be performed on the grid 
 * @author AGANDHAM
 *
 */
public class RowActionDTO implements Payload {

	private static final long serialVersionUID = 3136850242601497525L;
	public static final String VIEW_ACTION = "View";
	public static final String EDIT_ACTION = "Edit";
	public static final String ADD_ACTION = "Add";
	public static final String DELETE_ACTION = "Delete";
	public static final String REMOVE_ACTION = "Remove";
	public static final String COPY_ACTION = "Copy";

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String style;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String value;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean emitData;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String url;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean customizeTitle;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<String> customTooltipList;
	
	public RowActionDTO() {
		
	}
	
	/**
	 * @param value
	 * @param title
	 * @param emitData
	 */
	public RowActionDTO(String value, String title, boolean emitData) {
		super();
		this.value = value;
		this.title = title;
		this.emitData = emitData;
	}




	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the emitData
	 */
	public boolean isEmitData() {
		return emitData;
	}
	/**
	 * @param emitData the emitData to set
	 */
	public void setEmitData(boolean emitData) {
		this.emitData = emitData;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the customizeTitle
	 */
	public boolean isCustomizeTitle() {
		return customizeTitle;
	}

	/**
	 * @param customizeTitle the customizeTitle to set
	 */
	public void setCustomizeTitle(boolean customizeTitle) {
		this.customizeTitle = customizeTitle;
	}

	/**
	 * @return the customTooltipList
	 */
	public List<String> getCustomTooltipList() {
		return customTooltipList;
	}

	/**
	 * @param customTooltipList the customTooltipList to set
	 */
	public void setCustomTooltipList(List<String> customTooltipList) {
		this.customTooltipList = customTooltipList;
	}

	public static RowActionDTO getEmitableRowAction(String actionType, String style) {
		RowActionDTO rowActionDTO = new RowActionDTO();
		rowActionDTO.setValue(actionType.toLowerCase());
		rowActionDTO.setEmitData(true);
		rowActionDTO.setTitle(actionType);
		if(C.is(style)) {
			rowActionDTO.setStyle(style);
			
		}
		return rowActionDTO;
	}
	
	public static RowActionDTO getNonEmitableRowAction(String actionType, String style, String url) {
		RowActionDTO rowActionDTO = new RowActionDTO();
		rowActionDTO.setValue(actionType.toLowerCase());
		rowActionDTO.setEmitData(false);
		rowActionDTO.setTitle(actionType);
		if(C.is(style)) {
			rowActionDTO.setStyle(style);
			
		}
		if(C.is(url)) {
			rowActionDTO.setUrl(url);
			
		}
		return rowActionDTO;
	}
	
	public static List<RowActionDTO> getStandardRowActions() {
		List<RowActionDTO> rowActions = new ArrayList<RowActionDTO>();
		rowActions.add(getEmitableRowAction(VIEW_ACTION, "fa fa-file-o padding-r3"));
		rowActions.add(getEmitableRowAction(EDIT_ACTION, "fa fa-edit padding-r3"));
		return rowActions;
	}
}
