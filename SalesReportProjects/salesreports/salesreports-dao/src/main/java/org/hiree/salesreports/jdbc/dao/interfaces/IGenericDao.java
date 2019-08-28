package org.hiree.salesreports.jdbc.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;

public interface IGenericDao extends Serializable {

	
	public List<DialogColumnRowInfoDTO> getGenericDataAllRowsInfoFromTable(String tableName);
	public DialogColumnRowInfoDTO getGenericDataSelectedRowInfo(String tableName,Object primaryKeyValue);
	public void updateGenericDataSelectedRowInfo(String tableName,Map<String, Object> parameters,Object primaryKey);
	public void insertGenericDataSelectedRowInfo(String tableName,Map<String, Object> parameters);
	public List<String> getTableColumnName(String tableName);
}
