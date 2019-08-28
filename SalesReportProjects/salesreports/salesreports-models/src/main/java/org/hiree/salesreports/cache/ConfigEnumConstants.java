package org.hiree.salesreports.cache;

public enum ConfigEnumConstants {
	  KEY_ATT("key","@key"),
	  DEST_FIELD_ATT("destField","@destField"),
	  SRC_FIELD_ATT("srcField","@srcField"),
	  ORDER_ATT("order","@order"),
	  SRC_OBJ_ATT("srcObj","@srcObj"),
	  DST_OBJ_ATT("dstObj","@dstObj"),
	  DST_PCK_NAME_ATT("dstPackageName","@dstPackageName"),
	  SOURCE_ATT("source","@source");
	  
	  
	  private String value;
	  private String xPath;

	  public String getValue() {
	    return this.value;
	  }

	  public String getXpath() {
	    return this.xPath;
	  }
	  // enum constructor - can not be public or protected
	  ConfigEnumConstants(String value, String xPath) {
	    this.value = value;
	    this.xPath = xPath;
	  }
}
