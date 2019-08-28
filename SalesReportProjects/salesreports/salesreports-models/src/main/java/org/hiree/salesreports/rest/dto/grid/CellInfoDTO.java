package org.hiree.salesreports.rest.dto.grid;
import java.util.ArrayList;
import java.util.List;

import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.hiree.salesreports.util.constant.C;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
public class CellInfoDTO implements Payload {

	private static final long serialVersionUID = -1167897328359096372L;
	//Actual content to be displayed on the grid
	private Object content;
	//content for the grid, for sorting, filtering, export etc
	private Object queryContent;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String contentType;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Payload menuParam;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean clickable = false;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String identifier;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean emitData;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String url;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<IconInfoDTO> imageList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<RowActionDTO> rowActionList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<UIComponentDTO> componentList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String style;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean customizeTitle;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<String> customTooltipList;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String tableName;
	

	public CellInfoDTO() {
		super();
	}
	
	/**
	 * @param content
	 */
	public CellInfoDTO(String content) {
		super();
		if(C.isNotEmpty(content)) {
			this.content = String.valueOf(content);
			this.queryContent = this.content;
		}
		this.contentType = C.MT;
	}
	
	public CellInfoDTO(Boolean content) {
		super();
		if(content != null) {
			this.content = content;
			this.queryContent = this.content;
		}
		this.contentType = C.MT;
	}
	
	/**
	 * @param content
	 */
	public CellInfoDTO(Long content) {
		super();
		if(content != null) {
			this.content = Long.parseLong(String.valueOf(content));
			this.queryContent = this.content;
		}
		this.contentType = C.MT;
	}
	
	public CellInfoDTO(Double content) {
		super();
		if(content != null) {
			this.content = Double.parseDouble(String.valueOf(content));
			this.queryContent = this.content;
		}
		this.contentType = C.MT;
	}

	/**
	 * @param content
	 * @param contentType
	 */
	public CellInfoDTO(String content, String contentType) {
		super();
		if(C.isNotEmpty(content)) {
			this.content = String.valueOf(content);
			this.queryContent = this.content;
		}
		this.contentType = contentType;
	}
	
	/**
	 * @param content
	 * @param contentType
	 */
	public CellInfoDTO(Long content, String contentType) {
		super();
		if(content != null) {
			this.content = Long.parseLong(String.valueOf(content));
			this.queryContent = this.content;
		}
		this.contentType = contentType;
	}

	/**
	 * @return the content
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Object content) {
		this.content = content;
	}
	
	/**
	 * @return the queryContent
	 */
	public Object getQueryContent() {
		return queryContent != null ? queryContent : content;
	}

	/**
	 * @param queryContent the queryContent to set
	 */
	public void setQueryContent(Object queryContent) {
		this.queryContent = queryContent;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * @return the title
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the menuParam
	 */
	public Payload getMenuParam() {
		return menuParam;
	}

	/**
	 * @param menuParam
	 *            the menuParam to set
	 */
	public void setMenuParam(Payload menuParam) {
		this.menuParam = menuParam;
	}

	/**
	 * @return the clickable
	 */
	public Boolean getClickable() {
		return clickable;
	}

	/**
	 * @param clickable
	 *            the clickable to set
	 */
	public void setClickable(Boolean clickable) {
		this.clickable = clickable;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the emitData
	 */
	public boolean isEmitData() {
		return emitData;
	}

	/**
	 * @param emitData
	 *            the emitData to set
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
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the imageList
	 */
	public List<IconInfoDTO> getImageList() {
		return imageList;
	}

	public List<IconInfoDTO> getImageListNotNull() {
		if(C.isnt(imageList)) {
			imageList = new ArrayList<IconInfoDTO>();
		}
		return imageList;
	}
	/**
	 * @param imageList
	 *            the imageList to set
	 */
	public void setImageList(List<IconInfoDTO> imageList) {
		this.imageList = imageList;
	}

	/**
	 * @return the rowActionList
	 */
	public List<RowActionDTO> getRowActionList() {
		return rowActionList;
	}
	
	@JsonIgnore
	public List<RowActionDTO> getRowActionListNotNull() {
		if(rowActionList == null) {
			rowActionList = new ArrayList<RowActionDTO>();
		}
		return rowActionList; 
	}

	/**
	 * @param rowActionList the rowActionList to set
	 */
	public void setRowActionList(List<RowActionDTO> rowActionList) {
		this.rowActionList = rowActionList;
	}
	
	@JsonIgnore
	public List<UIComponentDTO> getComponentListNotNull() {
		if(componentList == null) {
			componentList = new ArrayList<UIComponentDTO>();
		}
		return componentList;
	}

	/**
	 * @return the componentList
	 */
	public List<UIComponentDTO> getComponentList() {
		return componentList;
	}

	/**
	 * @param componentList the componentList to set
	 */
	public void setComponentList(List<UIComponentDTO> componentList) {
		this.componentList = componentList;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
