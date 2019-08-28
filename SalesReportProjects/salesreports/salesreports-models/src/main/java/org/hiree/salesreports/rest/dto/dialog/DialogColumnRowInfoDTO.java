package org.hiree.salesreports.rest.dto.dialog;

import java.util.ArrayList;
import java.util.List;

import org.hiree.salesreports.rest.dto.interfaces.Payload;

public class DialogColumnRowInfoDTO implements Payload{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6116276607210291485L;
	private String tableName = null;
	
	private List<RowColumnInfoDTO> rowcoulmn = new ArrayList<RowColumnInfoDTO>();
	public List<RowColumnInfoDTO> getRowcoulmn() {
		return rowcoulmn;
	}
	public void setRowcoulmn(List<RowColumnInfoDTO> rowcoulmn) {
		this.rowcoulmn = rowcoulmn;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	
	
}
