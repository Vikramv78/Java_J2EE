package org.hiree.salesreports.util.enums;

public enum Test {
	ACTION_FIELD("action"),
	ACTION_TITLE("ACTION"),
	EMP_NO_FIELD("empNumber","numeric"),
	EMP_NO_TITLE("EMP_NO"),
	DEPT_FIELD("dept","numeric"),
	DEPT_TITLE("DEPT"),

	NAME_FIELD("name"),
	NAME_TITLE("NAME");
	
	
	
	
	
	private  String text;
	private String dataType;
	
	private Test(final String text) {
		this.text = text;
		
	}
	/**
	 * @param text
	 */
	private Test(final String text,final String dataType) {
		this.text = text;
		this.dataType = dataType;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}
}
