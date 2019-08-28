package org.hiree.salesreports.jdbc.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hiree.salesreports.rest.dto.TestTableDTO;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;

public interface ITestDao extends Serializable {

	 @SuppressWarnings("rawtypes")
	 public List<TestTableDTO> getAllTestData();
	
	 public TestTableDTO getTestData(String id);
	 
	 public DialogColumnRowInfoDTO getDialogColumnRowInfo(String id);
	 public List<String> getTableColumnName(String tableName);
	 public List<DialogColumnRowInfoDTO> getDialogColumnRowInfoList(String tableName);
	 public void updateGenericData(String tableName,Map<String, Object> parameters,Object primaryKey);
}
