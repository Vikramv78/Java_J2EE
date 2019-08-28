package org.hiree.salesreports.services.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import org.hiree.salesreports.rest.dto.TestTableDTO;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.rest.dto.grid.GridInfoDTO;

public interface ITestService  extends Serializable {
	 List<TestTableDTO> getTestData() throws Exception;
	 Future<String> process() throws InterruptedException;
	 void asyncMethodWithConfiguredExecutor();
	 GridInfoDTO getTestTableData();
	 DialogColumnRowInfoDTO getTestTableRecord(String id);
	 DialogColumnRowInfoDTO getGenericRecord(String id);
	 GridInfoDTO getGenericTableData(String tableName);
	 void updateGenericRecord(String tableName,DialogColumnRowInfoDTO dialogColumnRowInfoDTO);
}
