package org.hiree.salesreports.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hiree.salesreports.jdbc.dao.interfaces.IGenericDao;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.rest.dto.dialog.RowColumnInfoDTO;
import org.hiree.salesreports.rest.dto.grid.CellInfoDTO;
import org.hiree.salesreports.rest.dto.grid.ColumnInfoDTO;
import org.hiree.salesreports.rest.dto.grid.GridInfoDTO;
import org.hiree.salesreports.rest.dto.grid.IconInfoDTO;
import org.hiree.salesreports.rest.dto.grid.RowInfoDTO;
import org.hiree.salesreports.services.interfaces.IGenericDataService;
import org.hiree.salesreports.util.enums.IconStylesEnum;
import org.hiree.salesreports.util.enums.TableMappingEnum;
import org.hiree.salesreports.util.enums.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("genericDataService")
public class GenericDataServiceImpl implements IGenericDataService {

	@Autowired
	private IGenericDao genericDao;

	Logger logger = LoggerFactory.getLogger(GenericDataServiceImpl.class);

	@Override
	public GridInfoDTO getGenericDataAllRowsInfoFromTable(String tableName) {

		GridInfoDTO gridInfoDTO = new GridInfoDTO();
		try { // populate Columns
			gridInfoDTO.getColumns()
					.add(new ColumnInfoDTO(Test.ACTION_FIELD.toString(), Test.ACTION_TITLE.toString(), 200));
			List<String> columnList = genericDao.getTableColumnName(tableName);
			for (String col : columnList) {
				gridInfoDTO.getColumns()
						.add(new ColumnInfoDTO(TableMappingEnum.getTestEnumsMap(tableName).get(col), col, 200));

			}
			RowInfoDTO row = null;
			Map<String, CellInfoDTO> columnDataMap = null;
			List<RowInfoDTO> rowInfoDTOList = new ArrayList<>();
			gridInfoDTO.setRows(rowInfoDTOList);

			List<DialogColumnRowInfoDTO> obj = genericDao.getGenericDataAllRowsInfoFromTable(tableName);
			for (DialogColumnRowInfoDTO dialogColumnRowInfoDTO : obj) {
				row = new RowInfoDTO();
				columnDataMap = new HashMap<>();
				/*CellInfoDTO cellInfoDTO = getCellInfoDTO("Edit");
				cellInfoDTO.setClickable(true);
				cellInfoDTO.setEmitData(true);
				cellInfoDTO.setIdentifier(tableName);*/
				
				CellInfoDTO cellInfoDTO =getCellInfoImageDTO();
				cellInfoDTO.setTableName(tableName);
				
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

	protected CellInfoDTO getCellInfoDTO(String content) {
		CellInfoDTO cellInfoDTO = new CellInfoDTO(content);
		cellInfoDTO.setTitle(content);
		return cellInfoDTO;
	}

	protected CellInfoDTO getCellInfoImageDTO() {
		CellInfoDTO cellInfoDTO = new CellInfoDTO("");
		cellInfoDTO.setImageList(new ArrayList<IconInfoDTO>());
		String title = "EDIT";
		IconInfoDTO iconInfo = addIconImage(IconStylesEnum.EDIT_IMG_PLAIN_FA.toString(), title, title);
		iconInfo.setEmitData(true);		
		cellInfoDTO.getImageList().add(iconInfo);
		
		String deleteTitle = "DELETE";
		iconInfo = addIconImage(IconStylesEnum.DELETE_IMG_TRASH_O.toString(), deleteTitle, deleteTitle);
		iconInfo.setEmitData(true);		
		cellInfoDTO.getImageList().add(iconInfo);
		
		cellInfoDTO.setIdentifier("EDIT");
		
		return cellInfoDTO;
	}
	
	public IconInfoDTO addIconImage(String iconName , String title , String value){
		return addIconImage(iconName, title, value, true, true);
	}

	public IconInfoDTO addIconImage(String iconName , String title , String value, boolean clickable, boolean emitData){
		IconInfoDTO icon = null;
		icon = new IconInfoDTO();
		icon.setValue(value);
		icon.setClickable(clickable);
		icon.setEmitData(emitData);
		icon.setStyle(iconName);
		icon.setTitle(title);
		return icon;
	}
	@Override
	public DialogColumnRowInfoDTO getGenericDataSelectedRowInfo(String tableName, Object primaryKeyValue) {
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = new DialogColumnRowInfoDTO();
		if(null!= primaryKeyValue && primaryKeyValue.toString().equals("0")){
			dialogColumnRowInfoDTO = getGenericDataSelectedRowInfoForAdd(tableName);
		}else{
			dialogColumnRowInfoDTO = genericDao.getGenericDataSelectedRowInfo(tableName, primaryKeyValue);
		}
		

		return dialogColumnRowInfoDTO;
	}
	
	
	public DialogColumnRowInfoDTO getGenericDataSelectedRowInfoForAdd(String tableName) {
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = new DialogColumnRowInfoDTO();

		List<String> columnList = genericDao.getTableColumnName(tableName);
		int count = 0;
		for (String col : columnList) {
			RowColumnInfoDTO rowColumnInfoDTO = new RowColumnInfoDTO();

			rowColumnInfoDTO.setFieldName(col);
			rowColumnInfoDTO.setFieldValue("");

			rowColumnInfoDTO.setOrder(count);

			rowColumnInfoDTO.setType("textbox");
			dialogColumnRowInfoDTO.getRowcoulmn().add(rowColumnInfoDTO);
			dialogColumnRowInfoDTO.setTableName(tableName);
			count++;
		}

		return dialogColumnRowInfoDTO;
	}

	


	@Override
	public void updateGenericDataSelectedRowInfo(String tableName, DialogColumnRowInfoDTO dialogColumnRowInfoDTO) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		Object value = null;
		value = populateParamsMap(tableName, dialogColumnRowInfoDTO, parameters, value,false);
		genericDao.updateGenericDataSelectedRowInfo(tableName, parameters, value);

	}

	private Object populateParamsMap(String tableName, DialogColumnRowInfoDTO dialogColumnRowInfoDTO,
			Map<String, Object> parameters, Object value,boolean isNew) {
		if (dialogColumnRowInfoDTO.getRowcoulmn().size() > 0) {
			for (RowColumnInfoDTO rowColumnInfoDTO : dialogColumnRowInfoDTO.getRowcoulmn()) {
				if(isNew){
					
						parameters.put(rowColumnInfoDTO.getFieldName(), rowColumnInfoDTO.getFieldValue());
					
				}else{
					if (!rowColumnInfoDTO.getFieldName().equals(TableMappingEnum.getRowId(tableName))) {
						parameters.put(rowColumnInfoDTO.getFieldName(), rowColumnInfoDTO.getFieldValue());
					} else {
						value = rowColumnInfoDTO.getFieldValue();
					}
				}
				

			}

		}
		return value;
	}
	
	@Override
	public void createGenericDataSelectedRowInfo(String tableName, DialogColumnRowInfoDTO dialogColumnRowInfoDTO) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Object value = null;
		populateParamsMap(tableName, dialogColumnRowInfoDTO, parameters, value,true);
		genericDao.insertGenericDataSelectedRowInfo(tableName, parameters);
	}

}
