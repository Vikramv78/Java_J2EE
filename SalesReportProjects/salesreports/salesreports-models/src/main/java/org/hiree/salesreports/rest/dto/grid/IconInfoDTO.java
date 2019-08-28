/**
* @(#) IconInfoDTO.java
* @author AGANDHAM
* @date Oct 4, 2017
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

public class IconInfoDTO implements Payload {

	private static final long serialVersionUID = -6708211565860813819L;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String value;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String text;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String style;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean clickable;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean emitData;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String url;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean customizeTitle;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<String> customTooltipList;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	public String getCustomTitle() {
		return "";
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
	 * @return the clickable
	 */
	public boolean isClickable() {
		return clickable;
	}
	/**
	 * @param clickable the clickable to set
	 */
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
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
}


