package org.hiree.salesreports.rest.dto.grid;

import java.util.ArrayList;
import java.util.List;

import org.hiree.salesreports.rest.dto.interfaces.Payload;

public class ColumnInfoDTO implements Payload{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4130674167429699967L;

	/**
	 * Any updates here, make sure to replicate on the UI side too.
	 */

	public static final String COLUMN_TYPE_SMPL = "SIMPLE";
	public static final String  COLUMN_TYPE_SPN = "SPAN";
	public static final String COLUMN_TYPE_GRP = "GROUP";

	private String field;
	private String title;
	private Integer width;
	private String icon;
	private boolean resizable = true;
	private int columnIndex;
	private String footerStyle;
	private String dataType = "text";
	private String uiComponent;
	private String align;
	private boolean sortable = true;
	private boolean groupable = true;
	private boolean filterable = true;
	private boolean locked = false;
	private boolean reorderable = true;
	//private Map<Integer, List<SelectItem>> componentOptionsData;
	private String columnType = COLUMN_TYPE_SMPL;   // or GROUP or "SPAN"
	private List<ColumnInfoDTO> subColumnList = new ArrayList<>();
	private boolean enableFooter;
	private String footerValue;

	/**
	 * @param field
	 * @param title
	 * @param sortable
	 */
	public ColumnInfoDTO(String field, String title, boolean sortable) {
		super();
		this.field = field;
		this.title = title;
		this.sortable = sortable;
	}
	public ColumnInfoDTO(String field, String title, String dataType) {
		super();
		this.field = field;
		this.title = title;
		this.dataType = dataType;
	}

	public ColumnInfoDTO(String field, String title, Integer width, boolean filterable) {
		super();
		this.field = field;
		this.title = title;
		this.width = width;
		this.filterable = filterable;
	}
	public ColumnInfoDTO(String field, boolean filterable ,String title) {
      super();
      this.field = field;
      this.filterable = filterable;
      this.title = title;
  }
	/**
	 * @param field
	 * @param title
	 */
	public ColumnInfoDTO(String field, String title) {
		super();
		this.field = field;
		this.title = title;
	}
	
	
	public ColumnInfoDTO(String field, String title, int width) {
		super();
		this.field = field;
		this.title = title;
		this.width = width;
	}
	
	public ColumnInfoDTO(String field, String title, String dataType, int width) {
		super();
		this.field = field;
		this.title = title;
		this.dataType = dataType;
		this.width = width;
	}
	
	public ColumnInfoDTO(String field, int width) {
		super();
		this.field = field;
		this.width = width;
	}


	public ColumnInfoDTO() {
		super();
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
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
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * @param resizable
	 *            the resizable to set
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	/**
	 * @return the columnIndex
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	/**
	 * @param columnIndex
	 *            the columnIndex to set
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}	
	
	/**
	 * @return the footerStyle
	 */
	public String getFooterStyle() {
		return footerStyle;
	}
	/**
	 * @param footerStyle the footerStyle to set
	 */
	public void setFooterStyle(String footerStyle) {
		this.footerStyle = footerStyle;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the uiComponent
	 */
	public String getUiComponent() {
		return uiComponent;
	}

	/**
	 * @param uiComponent
	 *            the uiComponent to set
	 */
	public void setUiComponent(String uiComponent) {
		this.uiComponent = uiComponent;
	}

	/**
	 * @return the align
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @param align
	 *            the align to set
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @return the sortable
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * @param sortable
	 *            the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	
	/**
	 * @return the groupable
	 */
	public boolean isGroupable() {
		return groupable;
	}
	/**
	 * @param groupable the groupable to set
	 */
	public void setGroupable(boolean groupable) {
		this.groupable = groupable;
	}
	/**
	 * @return the filterable
	 */
	public boolean isFilterable() {
		return filterable;
	}
	/**
	 * @param filterable the filterable to set
	 */
	public void setFilterable(boolean filterable) {
		this.filterable = filterable;
	}
	
	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 *            the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	/**
	 * @return the reorderable
	 */
	public boolean isReorderable() {
		return reorderable;
	}
	/**
	 * @param reorderable the reorderable to set
	 */
	public void setReorderable(boolean reorderable) {
		this.reorderable = reorderable;
	}
	/**
	 * @return the columntype
	 */
	public String getColumnType() {
		return columnType;
	}

	/**
	 * @param columntype the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	/**
	 * @return the subColumnList
	 */
	public List<ColumnInfoDTO> getSubColumnList() {
		return subColumnList;
	}

	/**
	 * @param subColumnList the subColumnList to set
	 */
	public void setSubColumnList(List<ColumnInfoDTO> subColumnList) {
		this.subColumnList = subColumnList;
	}
	
	/**
	 * @return the enableFooter
	 */
	public boolean isEnableFooter() {
		return enableFooter;
	}
	/**
	 * @param enableFooter the enableFooter to set
	 */
	public void setEnableFooter(boolean enableFooter) {
		this.enableFooter = enableFooter;
	}
	/**
	 * @return the footerValue
	 */
	public String getFooterValue() {
		return footerValue;
	}
	/**
	 * @param footerValue the footerValue to set
	 */
	public void setFooterValue(String footerValue) {
		this.footerValue = footerValue;
	}


}
