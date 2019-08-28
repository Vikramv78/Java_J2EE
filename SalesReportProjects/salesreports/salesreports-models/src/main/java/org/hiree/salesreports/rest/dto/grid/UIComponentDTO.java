/**
* @(#) UIComponentDTO.java
* @author AGANDHAM
* @date Jan 31, 2018
*
* 
* Copyright (c) 2006 Educational Testing Service. All Rights Reserved.
*
* This software is the confidential and proprietary information of Educational
* Testing Service. ("Confidential Information").
*/

package org.hiree.salesreports.rest.dto.grid;

import java.util.List;

import org.hiree.salesreports.rest.dto.interfaces.Payload;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UIComponentDTO implements Payload {

	private static final long serialVersionUID = -4731096517730087840L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String identifier;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object selValue;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;
/*	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<SelectItem> optionsData;*/
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean disabled;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean required;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String pattern;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object minValue;  // for numeric textboxes
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object maxValue;  // for numeric textboxes
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean makeRowSelected = false;  // for numeric textboxes
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean customizeTitle;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> customTooltipList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean usePrimitive = true;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String placeHolder = "Select";
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String width;

	
	
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the selValue
	 */
	public Object getSelValue() {
		return selValue;
	}

	/**
	 * @param selValue
	 *            the selValue to set
	 */
	public void setSelValue(Object selValue) {
		this.selValue = selValue;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the optionsData
	 *//*
	public List<SelectItem> getOptionsData() {
		return optionsData;
	}

	*//**
	 * @param optionsData
	 *            the optionsData to set
	 *//*
	public void setOptionsData(List<SelectItem> optionsData) {
		this.optionsData = optionsData;
	}
*/
	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the minValue
	 */
	public Object getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(Object minValue) {
		this.minValue = minValue;
	}
	
	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(Object maxValue) {
		this.maxValue = maxValue;
	}
	
	/**
	 * @return the maxValue
	 */
	public Object getMaxValue() {
		return maxValue;
	}

	/**
	 * @return the makeRowSelected
	 */
	public boolean isMakeRowSelected() {
		return makeRowSelected;
	}

	/**
	 * @param makeRowSelected the makeRowSelected to set
	 */
	public void setMakeRowSelected(boolean makeRowSelected) {
		this.makeRowSelected = makeRowSelected;
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

	/**
	 * @return the usePrimitive
	 */
	public boolean isUsePrimitive() {
		return usePrimitive;
	}

	/**
	 * @param usePrimitive the usePrimitive to set
	 */
	public void setUsePrimitive(boolean usePrimitive) {
		this.usePrimitive = usePrimitive;
	}

	/**
	 * @return the placeHolder
	 */
	public String getPlaceHolder() {
		return placeHolder;
	}

	/**
	 * @param placeHolder the placeHolder to set
	 */
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}
}
