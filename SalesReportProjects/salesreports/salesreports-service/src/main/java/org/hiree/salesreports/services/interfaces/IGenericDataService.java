package org.hiree.salesreports.services.interfaces;

import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.rest.dto.grid.GridInfoDTO;

public interface IGenericDataService {
	public GridInfoDTO getGenericDataAllRowsInfoFromTable(String tableName);
	public DialogColumnRowInfoDTO getGenericDataSelectedRowInfo(String tableName,Object primaryKeyValue);
	public void updateGenericDataSelectedRowInfo(String tableName, DialogColumnRowInfoDTO dialogColumnRowInfoDTO);
	public void createGenericDataSelectedRowInfo(String tableName, DialogColumnRowInfoDTO dialogColumnRowInfoDTO);
	
}
