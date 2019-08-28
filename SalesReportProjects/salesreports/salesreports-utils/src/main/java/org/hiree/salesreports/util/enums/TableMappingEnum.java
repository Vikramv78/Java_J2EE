package org.hiree.salesreports.util.enums;

import java.util.HashMap;
import java.util.Map;

public enum TableMappingEnum {
	EMP_NO("ENO","empNumber", "TEST",true,"SOLD_USING_ID_SEQ.nextval"),
	DEPT("DEPT","dept", "TEST"),
	NAME("NAME","name", "TEST");
	

	private  String key;
	private String value;
	private String table;
	private boolean rowId;
	private String sequence;

	
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	
	public static  Map<String, String> getTestEnumsMap(String tableName) {
		return tablemap.get(tableName);
	}
	
	public static String getRowId(String tableName) {
		return tableRowIdMap.get(tableName);
	}
	
	public static String getSequenceName(String tableName) {
		return tableSequenceMap.get(tableName);
	}
	
	
	
	/**
	 * @param text
	 */
	private TableMappingEnum(final String key,final String value,String table) {
		this.key = key;
		this.value = value;
		this.table=table;
	}
	
	private TableMappingEnum(final String key,final String value,String table,boolean rowId) {
		this.key = key;
		this.value = value;
		this.table=table;
		this.rowId=rowId;
	}
	private TableMappingEnum(final String key,final String value,String table,boolean rowId,String sequence) {
		this.key = key;
		this.value = value;
		this.table=table;
		this.rowId=rowId;
		this.sequence=sequence;
	}
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getTable() {
		return table;
	}

	

	private static final Map<String ,HashMap<String, String>> tablemap = new HashMap<String ,HashMap<String, String>>();
	private static final Map<String, String> tableRowIdMap = new HashMap<String ,String>();
	private static final Map<String, String> tableSequenceMap = new HashMap<String ,String>();
	
	static {
		for (TableMappingEnum tableEnums : TableMappingEnum.values()) {
			HashMap<String, String> filedMap = null;
			if (tablemap.containsKey(tableEnums.table)) {
				filedMap = tablemap.get(tableEnums.table);
			} else {
				filedMap = new HashMap<String, String>();
				tablemap.put(tableEnums.table, filedMap);
			}

			filedMap.put(tableEnums.key, tableEnums.value);

		}
		for (TableMappingEnum tableEnums : TableMappingEnum.values()) {
			
			if (tableEnums.rowId) {
				tableRowIdMap.put(tableEnums.table, tableEnums.key);
				tableSequenceMap.put(tableEnums.table, tableEnums.sequence);
			} 

		}
		
	}
}
