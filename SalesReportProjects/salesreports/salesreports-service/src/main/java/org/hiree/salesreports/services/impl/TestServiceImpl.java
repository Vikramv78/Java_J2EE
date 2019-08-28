package org.hiree.salesreports.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.hiree.salesreports.jdbc.dao.interfaces.ITestDao;
import org.hiree.salesreports.rest.dto.TestTableDTO;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.rest.dto.dialog.RowColumnInfoDTO;
import org.hiree.salesreports.rest.dto.grid.CellInfoDTO;
import org.hiree.salesreports.rest.dto.grid.ColumnInfoDTO;
import org.hiree.salesreports.rest.dto.grid.GridInfoDTO;
import org.hiree.salesreports.rest.dto.grid.RowInfoDTO;
import org.hiree.salesreports.services.interfaces.ITestService;
import org.hiree.salesreports.util.enums.TableMappingEnum;
import org.hiree.salesreports.util.enums.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements ITestService {

	@Autowired
	private ITestDao testDao;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3049392033930694314L;
	Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	public List<TestTableDTO> getTestData() throws Exception{
		List<TestTableDTO> obj = testDao.getAllTestData();
		return obj;
	}
	
	@Override
	public GridInfoDTO getTestTableData(){

		GridInfoDTO gridInfoDTO = new GridInfoDTO();
		try {  //populate Columns
			gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.ACTION_FIELD.toString(),Test.ACTION_TITLE.toString(),200));
			gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.EMP_NO_FIELD.getText(),Test.EMP_NO_TITLE.toString(),Test.EMP_NO_FIELD.getDataType(),200));
			gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.NAME_FIELD.toString(),Test.NAME_TITLE.toString(),200));
			gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.DEPT_FIELD.toString(),Test.DEPT_TITLE.toString(),200));
			
			RowInfoDTO row = null;
			Map<String, CellInfoDTO> columnDataMap = null;
			List<RowInfoDTO> rowInfoDTOList = new ArrayList<>();
			gridInfoDTO.setRows(rowInfoDTOList);

			List<TestTableDTO> obj = testDao.getAllTestData();
			for(TestTableDTO testDto : obj) {
				row = new RowInfoDTO();
				columnDataMap = new HashMap<>();
				CellInfoDTO cellInfoDTO = getCellInfoDTO("Edit");
				cellInfoDTO.setClickable(true);
				cellInfoDTO.setEmitData(true);
				cellInfoDTO.setIdentifier("TESTVO");
				columnDataMap.put(Test.ACTION_FIELD.toString(), cellInfoDTO);
				columnDataMap.put(Test.EMP_NO_FIELD.toString(), new CellInfoDTO(Long.parseLong(String.valueOf(testDto.getEmpNumber()))));
				columnDataMap.put(Test.NAME_FIELD.toString(), new CellInfoDTO(testDto.getName()));
				columnDataMap.put(Test.DEPT_FIELD.toString(), new CellInfoDTO(testDto.getDept()));
				
				columnDataMap.put(GridInfoDTO.ROW_ID, new CellInfoDTO(String.valueOf(testDto.getEmpNumber())));
				CellInfoDTO menuPram = new CellInfoDTO("");
				menuPram.setMenuParam(testDto);
				columnDataMap.put(GridInfoDTO.MENU_PARAM, menuPram);
				
				row.setColumnMap(columnDataMap);
				rowInfoDTOList.add(row);

			}
		} catch (Exception e) {	
			logger.error("Exception:", e);
			
		}

		return gridInfoDTO;
	}
	
	
	public DialogColumnRowInfoDTO getTestTableRecord(String id){
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = new DialogColumnRowInfoDTO();
	
		TestTableDTO testTableDTO= testDao.getTestData(id);
		
		RowColumnInfoDTO rowColumnInfoDTO = new RowColumnInfoDTO();
		rowColumnInfoDTO.setFieldName("EmpNumber");
		rowColumnInfoDTO.setType("textbox");
		rowColumnInfoDTO.setFieldValue(String.valueOf(testTableDTO.getEmpNumber()));
		rowColumnInfoDTO.setOrder(1);
		RowColumnInfoDTO rowColumnInfoDTO1 = new RowColumnInfoDTO();
		rowColumnInfoDTO1.setFieldName("Name");
		rowColumnInfoDTO1.setType("textbox");
		rowColumnInfoDTO1.setFieldValue(String.valueOf(testTableDTO.getName()));
		rowColumnInfoDTO1.setOrder(2);
		RowColumnInfoDTO rowColumnInfoDTO2 = new RowColumnInfoDTO();
		rowColumnInfoDTO2.setFieldName("Dept");
		rowColumnInfoDTO2.setType("textbox");
		rowColumnInfoDTO2.setFieldValue(String.valueOf(testTableDTO.getDept()));
		rowColumnInfoDTO2.setOrder(3);
	
		
		dialogColumnRowInfoDTO.getRowcoulmn().add(rowColumnInfoDTO);
		dialogColumnRowInfoDTO.getRowcoulmn().add(rowColumnInfoDTO1);
		dialogColumnRowInfoDTO.getRowcoulmn().add(rowColumnInfoDTO2);
		return dialogColumnRowInfoDTO;
	}
	
/*	public GridInfoDTO getGenericTableData(){

		GridInfoDTO gridInfoDTO = new GridInfoDTO();
		try {  //populate Columns
			gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.ACTION_FIELD.toString(),Test.ACTION_TITLE.toString(),200));
			List<String> columnList=testDao.getTableColumnName("test");
			for(String col: columnList){
				//System.out.println("col KKKKKKKKKKKKKKKK -->"+ col);
				gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.EMP_NO_FIELD.getText(),Test.EMP_NO_TITLE.toString(),Test.EMP_NO_FIELD.getDataType(),200));
				gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.NAME_FIELD.toString(),Test.NAME_TITLE.toString(),200));
				gridInfoDTO.getColumns().add(new ColumnInfoDTO(Test.DEPT_FIELD.toString(),Test.DEPT_TITLE.toString(),200));
				gridInfoDTO.getColumns().add(new ColumnInfoDTO(TableMappingEnum.getTestEnumsMap().get(col),col,200));
				
			}
			
			
			RowInfoDTO row = null;
			Map<String, CellInfoDTO> columnDataMap = null;
			List<RowInfoDTO> rowInfoDTOList = new ArrayList<>();
			gridInfoDTO.setRows(rowInfoDTOList);
		
			List<DialogColumnRowInfoDTO> obj = testDao.getDialogColumnRowInfoList();
			for(DialogColumnRowInfoDTO dialogColumnRowInfoDTO : obj) {
				row = new RowInfoDTO();
				columnDataMap = new HashMap<>();
				CellInfoDTO cellInfoDTO = getCellInfoDTO("Edit");
				cellInfoDTO.setClickable(true);
				cellInfoDTO.setEmitData(true);
				cellInfoDTO.setIdentifier("TESTVO");
				columnDataMap.put(Test.ACTION_FIELD.toString(), cellInfoDTO);
				for(RowColumnInfoDTO rowColumnInfoDTO : dialogColumnRowInfoDTO.getRowcoulmn()){
					for(String col: columnList){
						if(rowColumnInfoDTO.getFieldName().equals(col)){
							columnDataMap.put(TableMappingEnum.getTestEnumsMap().get(col),  new CellInfoDTO(rowColumnInfoDTO.getFieldValue()));
							if(rowColumnInfoDTO.getFieldName().equals("EMP_NO")){
								columnDataMap.put(GridInfoDTO.ROW_ID, new CellInfoDTO(String.valueOf(rowColumnInfoDTO.getFieldValue())));
							}
						}
						
					}
				}
				
			
				
				
				columnDataMap.put(Test.EMP_NO_FIELD.toString(), new CellInfoDTO(Long.parseLong(String.valueOf(testDto.getEmpNumber()))));
				columnDataMap.put(Test.NAME_FIELD.toString(), new CellInfoDTO(testDto.getName()));
				columnDataMap.put(Test.DEPT_FIELD.toString(), new CellInfoDTO(testDto.getDept()));
				
				
				CellInfoDTO menuPram = new CellInfoDTO("");
				menuPram.setMenuParam(dialogColumnRowInfoDTO);
				columnDataMap.put(GridInfoDTO.MENU_PARAM, menuPram);
				
				row.setColumnMap(columnDataMap);
				rowInfoDTOList.add(row);

			}
		} catch (Exception e) {	
			logger.error("Exception:", e);
			
		}

		return gridInfoDTO;
	}
	*/
	public GridInfoDTO getGenericTableData(String tableName) {

		GridInfoDTO gridInfoDTO = new GridInfoDTO();
		try { // populate Columns
			gridInfoDTO.getColumns()
					.add(new ColumnInfoDTO(Test.ACTION_FIELD.toString(), Test.ACTION_TITLE.toString(), 200));
			List<String> columnList = testDao.getTableColumnName(tableName);
			for (String col : columnList) {
				gridInfoDTO.getColumns()
						.add(new ColumnInfoDTO(TableMappingEnum.getTestEnumsMap(tableName).get(col), col, 200));

			}
			RowInfoDTO row = null;
			Map<String, CellInfoDTO> columnDataMap = null;
			List<RowInfoDTO> rowInfoDTOList = new ArrayList<>();
			gridInfoDTO.setRows(rowInfoDTOList);

			List<DialogColumnRowInfoDTO> obj = testDao.getDialogColumnRowInfoList(tableName);
			for (DialogColumnRowInfoDTO dialogColumnRowInfoDTO : obj) {
				row = new RowInfoDTO();
				columnDataMap = new HashMap<>();
				CellInfoDTO cellInfoDTO = getCellInfoDTO("Edit");
				cellInfoDTO.setClickable(true);
				cellInfoDTO.setEmitData(true);
				cellInfoDTO.setIdentifier(tableName);
				columnDataMap.put(Test.ACTION_FIELD.toString(), cellInfoDTO);
				for (RowColumnInfoDTO rowColumnInfoDTO : dialogColumnRowInfoDTO.getRowcoulmn()) {
					for (String col : columnList) {
						if (rowColumnInfoDTO.getFieldName().equals(col)) {
							columnDataMap.put(TableMappingEnum.getTestEnumsMap(tableName).get(col),
									new CellInfoDTO(rowColumnInfoDTO.getFieldValue()));
							if (rowColumnInfoDTO.getFieldName().equals(TableMappingEnum.getRowId(tableName))) {
								columnDataMap.put(GridInfoDTO.ROW_ID,
										new CellInfoDTO(String.valueOf(rowColumnInfoDTO.getFieldValue())));
							}
						}

					}
				}

				CellInfoDTO menuPram = new CellInfoDTO("");
				menuPram.setMenuParam(dialogColumnRowInfoDTO);
				columnDataMap.put(GridInfoDTO.MENU_PARAM, menuPram);

				row.setColumnMap(columnDataMap);
				rowInfoDTOList.add(row);

			}
		} catch (Exception e) {
			logger.error("Exception:", e);

		}

		return gridInfoDTO;
	}
	

	public DialogColumnRowInfoDTO getGenericRecord(String id){
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = new DialogColumnRowInfoDTO();
		dialogColumnRowInfoDTO = testDao.getDialogColumnRowInfo(id);
	
		return dialogColumnRowInfoDTO;
	}
	
	public void updateGenericRecord(String tableName ,DialogColumnRowInfoDTO dialogColumnRowInfoDTO){
		Map<String, Object> parameters = new HashMap<String, Object>();
		Object value=null;
		if(dialogColumnRowInfoDTO.getRowcoulmn().size()>0){
			for(RowColumnInfoDTO rowColumnInfoDTO : dialogColumnRowInfoDTO.getRowcoulmn()){
				if(!rowColumnInfoDTO.getFieldName().equals(TableMappingEnum.getRowId(tableName))){
					parameters.put(rowColumnInfoDTO.getFieldName(), rowColumnInfoDTO.getFieldValue());
				}else{
					value =rowColumnInfoDTO.getFieldValue();
				}
				
			}
		
		 }
		
		 testDao.updateGenericData(tableName,parameters,value);
	}

	
	protected CellInfoDTO getCellInfoDTO(String content) {
		CellInfoDTO cellInfoDTO = new CellInfoDTO(content);
		cellInfoDTO.setTitle(content);
		return cellInfoDTO;
	}
	@Async("threadPoolTaskExecutor")
	public void asyncMethodWithConfiguredExecutor() {
		logger.info("Execute method with configured executor - "
	      + Thread.currentThread().getName());
		try {
			Thread.sleep(30000);
			int count =0;
			while(true){
				count++;
				logger.info("Execute method with configured executor count - "
					      + count);
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Async("threadPoolTaskExecutor")
    public Future<String> process() throws InterruptedException {
		
		logger.info("###Start Processing with Thread id: " + Thread.currentThread().getId());
		
		// Sleep 3s for simulating the processing
		Thread.sleep(30000);
		
		String processInfo = String.format("Processing is Done with Thread id= %d", Thread.currentThread().getId());
        return new AsyncResult<>(processInfo);
    }
}
