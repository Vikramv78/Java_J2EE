package org.hiree.salesreports.rest.dto.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hiree.salesreports.rest.dto.interfaces.Payload;

public class RowInfoDTO implements Payload{

	private static final long serialVersionUID = 1743563566343453647L;

	private Map<String, CellInfoDTO> columnMap = new HashMap<String, CellInfoDTO>();
	private List<RowInfoDTO> children = new ArrayList<RowInfoDTO>();
	private boolean inactive = false;
	private boolean deleted = false;
	private boolean selectable = true;
	private boolean isPreSelected = false;
	private String parentObjectId;
	/**
	 * 
	 */
	public RowInfoDTO() {
		super();
	}
	
	/**
	 * @return the columnMap
	 */
	public Map<String, CellInfoDTO> getColumnMap() {
		return columnMap;
	}
	/**
	 * @param columnMap the columnMap to set
	 */
	public void setColumnMap(Map<String, CellInfoDTO> columnMap) {
		this.columnMap = columnMap;
	}
	/**
	 * @return the children
	 */
	public List<RowInfoDTO> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<RowInfoDTO> children) {
		this.children = children;
	}

	/**
	 * @return the inactive
	 */
	public boolean isInactive() {
		return inactive;
	}

	/**
	 * @param inactive the inactive to set
	 */
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	
	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the selectable
	 */
	public boolean isSelectable() {
		return selectable;
	}

	/**
	 * @param selectable the selectable to set
	 */
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	/**
	 * @return the isPreSelected
	 */
	public boolean isPreSelected() {
		return isPreSelected;
	}

	/**
	 * @param isPreSelected the isPreSelected to set
	 */
	public void setPreSelected(boolean isPreSelected) {
		this.isPreSelected = isPreSelected;
	}

	/**
	 * @return the parentObjectId
	 */
	public String getParentObjectId() {
		return parentObjectId;
	}

	/**
	 * @param parentObjectId the parentObjectId to set
	 */
	public void setParentObjectId(String parentObjectId) {
		this.parentObjectId = parentObjectId;
	}
	
}
