package org.hiree.salesreports.rest.dto.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hiree.salesreports.rest.dto.grid.ColumnInfoDTO;
import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.hiree.salesreports.util.constant.C;

public class GridInfoDTO implements Payload{

	private static final long serialVersionUID = 7328164506861493558L;

	private List<ColumnInfoDTO> columns = new ArrayList<ColumnInfoDTO>();
	private List<RowInfoDTO> rows = new ArrayList<RowInfoDTO>();
	private String noDataFoundMsg;

	public final static String POSITION = "Position";
	public final static String ROW_ID = "ROW_ID";
	public final static String MENU_PARAM = "MENU_PARAM";
	public final static String VIEW_LINK = "View";
	public final static String EDIT_LINK = "Edit";
	public final static String ROW_INACTIVE = "ROW_INACTIVE";
	public final static String ROW_DELETED = "ROW_DELETED";
	public final static String ROW_SELECTABLE = "ROW_SELECTABLE";
	public final static String ROW_ACTION = "ROW_ACTION";
	public final static String CHECK_IN_OUT = "CHECK_IN_OUT";
	public final static String EXP_COLPSE = "EC";
	public final static String UI_TEXT_BOX = "textbox";
	public final static String UI_NUM_TEXT_BOX = "numtextbox";
	public final static String UI_RADIO_BTN = "radiobutton";
	public final static String UI_CHK_BOX = "checkbox";
	public final static String UI_DROP_DOWN = "dropdown";
	public final static String UI_COMBO_BOX = "combobox";
	public final static String UI_DATE_PICKER = "datepicker";
	public final static String SEQ_COL = "SEQ_COL";
	public final static String ROW_PRE_SELECTED = "ROW_PRE_SELECTED";
	public final static String UI_AUTO_CMPLT = "autocomplete";

	/**
	 * 
	 */
	public GridInfoDTO() {
		super();
		/*ColumnInfoDTO expcolCol = new ColumnInfoDTO(EXP_COLPSE, C.UNDERSCORE, false);
		expcolCol.setWidth(1);
		this.columns.add(expcolCol);*/
	}

	/**
	 * @return the columns
	 */
	public List<ColumnInfoDTO> getColumns() {
		return columns;
	}

	/**
	 * @return the rows
	 */
	public List<RowInfoDTO> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<RowInfoDTO> rows) {
		this.rows = rows;
	}

	public String getNoDataFoundMsg() {
		return noDataFoundMsg;
	}

	public void setNoDataFoundMsg(String noDataFoundMsg) {
		this.noDataFoundMsg = noDataFoundMsg;
	}

	/**
	 * This method gets called before sending the response back to the UI. This
	 * method calculates some important properties required for UI Grid component,
	 * based on the data populated.
	 */
	public void computeHierarchy() {
		if (CollectionUtils.isNotEmpty(rows)) {
			computeRowPositions(rows, 0, C.MT);
		}
	}

	/**
	 * This recursive method calculates the <strong>Position</strong> value for each
	 * row based on how deep it is from top most parent. The
	 * <strong>Position</strong> value contains a concatenated index value at each
	 * level separated by <strong>_</strong> <br/>
	 * This property is mandatory for UI Grid to display hierarchy.
	 * 
	 * @param rows
	 */
	private void computeRowPositions(List<RowInfoDTO> rows, int level, String position) {
		if (C.is(rows)) {
			int i = 0;
			for (RowInfoDTO row : rows) {
				Map<String, CellInfoDTO> columnMap = row.getColumnMap();

				CellInfoDTO delCellInfo = new CellInfoDTO(row.isDeleted());
				columnMap.put(ROW_DELETED, delCellInfo);

				CellInfoDTO inactiveCellInfo = new CellInfoDTO(row.isInactive());
				columnMap.put(ROW_INACTIVE, inactiveCellInfo);

				CellInfoDTO selCellInfo = new CellInfoDTO(row.isSelectable());
				columnMap.put(ROW_SELECTABLE, selCellInfo);

				CellInfoDTO rowPreSelected = new CellInfoDTO(row.isPreSelected());
				columnMap.put(ROW_PRE_SELECTED, rowPreSelected);

				CellInfoDTO cellInfo = null;
				String content = StringUtils.isNotEmpty(position) ? (position + C.UNDERSCORE + i) : String.valueOf(i);
				cellInfo = new CellInfoDTO(content);
				columnMap.put(POSITION, cellInfo);

				List<RowInfoDTO> children = row.getChildren();
				if (C.is(children)) {
					computeRowPositions(children, level + 1, content);
				}
				i++;
			}
		}
	}

/*	public void removeSpecifiedColumn(List<RowInfoDTO> rows, int level, String columnName,
			List<String> remSetStsCdeList) {
		if (C.is(rows)) {
			for (RowInfoDTO row : rows) {
				Map<String, CellInfoDTO> columnMap = row.getColumnMap();
				if (level == 1) {
					CellInfoDTO setStsCell = columnMap.get(DBColumnConstants.ITEM_SET_STS_VAL_COL_NAME);
					if (setStsCell != null) {
						String setStsCde = String.valueOf(setStsCell.getContent());
						if (setStsCde != null && remSetStsCdeList != null && remSetStsCdeList.contains(setStsCde)) {
							columnMap.remove(columnName);
						}
					}
				} else {
					columnMap.remove(columnName);
				}
				List<RowInfoDTO> children = row.getChildren();
				if (C.is(children)) {
					removeSpecifiedColumn(children, level + 1, columnName, remSetStsCdeList);
				}
			}
		}
	}*/

	/*public void removeSeqColumn(List<RowInfoDTO> rows, int level, String columnName, List<String> remSetStsCdeList) {
		if (C.is(rows)) {
			for (RowInfoDTO row : rows) {
				Map<String, CellInfoDTO> columnMap = row.getColumnMap();

				CellInfoDTO setStsCell = columnMap.get(DBColumnConstants.ITEM_SET_STS_VAL_COL_NAME);
				if (setStsCell != null) {
					String setStsCde = String.valueOf(setStsCell.getContent());
					if (setStsCde != null && remSetStsCdeList != null && remSetStsCdeList.contains(setStsCde)) {
						columnMap.remove(columnName);
					}
				}

				CellInfoDTO selForuseCell = columnMap.get(DBColumnConstants.ITEM_SELECT_FORUSE_COL_VAL);
				if (selForuseCell != null) {
					String selForuse = String.valueOf(selForuseCell.getContent());
					if (selForuse != null && (DBColumnConstants.FLAG_VALUE_NO.equals(selForuse))) {
						columnMap.remove(columnName);
					}
				}

				List<RowInfoDTO> children = row.getChildren();
				if (C.is(children)) {
					removeSeqColumn(children, level + 1, columnName, remSetStsCdeList);
				}
			}
		}
	}*/
}
