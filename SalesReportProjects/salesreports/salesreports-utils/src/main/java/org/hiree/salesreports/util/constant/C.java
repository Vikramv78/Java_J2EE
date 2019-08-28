/**
 * @(#)C.java
 * @author SVARMA001
 * @date Feb 22, 2016
 * 
 * $Id: C.java $
 * 
 * Copyright (c) 2016 Educational Testing Service. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Educational Testing Service.
 * ("Confidential Information").
 */
package org.hiree.salesreports.util.constant;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Commonly used constants and utility methods
 */
public class C {

	/**
	 * Y
	 */
	
	public static final char Y_CHR = 'Y';
	
	public static final String Y = "Y";
	/**
	 * N
	 */
	public static final String N = "N";
	/**
	 * Yes
	 */
	public static final String ID = "id";
	public static final String A = "A";
	public static final String YES = "Yes";
	
	public static final String LOWER_TRUE = "true";
	
	public static final String LOWER_FALSE = "false";
	/**
	 * No
	 */
	public static final String NO = "No";
	/**
	 * EMPTY_STRING ""
	 */
	public static final String EMPTY = "";
	/**
	 * EMPTY_STRING ""
	 */
	public static final String MT = EMPTY;
	
	public static final String PERCENT ="%";
	/**
	 * COMMA ","
	 */
	public static final String COMMA = ",";
	
	/**
	 * COMMA_SPACE ", "
	 */
	public static final String COMMA_SPACE = ", ";
	
	/**
	 * PERIOD "."
	 */
	public static final String PERIOD = ".";
	/**
	 * PERIOD "."
	 */
	public static final String DOT = PERIOD;
	/**
	 * SEMICOLON ";"
	 */
	public static final String SEMICOLON = ";";
	/**
	 * SEMICOLON ";"
	 */
	public static final String SC = SEMICOLON;
	/**
	 * COLON ":"
	 */
	public static final String COLON = ":";
	/**
	 * UNDERSCORE "_"
	 */
	public static final String UNDERSCORE = "_";
	/**
	 * UNDERSCORE "_"
	 */
	public static final String US = UNDERSCORE;
	/**
	 * PIPE "|"
	 */
	public static final String PIPE = "|";
	/**
	 * PIPE_WITH_SPACE " | "
	 */
	public static final String PIPE_WITH_SPACE = " | ";	
	/**
	 * AND "&"
	 */
	public static final String AND = "&";
	/**
	 * HYPHEN "-"
	 */
	public static final String HYPHEN = "-";
	/**
	 * HYPHEN "-"
	 */
	public static final String DASH = HYPHEN;
	/**
	 * COLON ":"
	 */
	public static final String SET_SPRTR = COLON;
	/**
	 * DOUBLEQUOTE "\""
	 */
	public static final String DOUBLEQUOTE = "\"";
	/**
	 * DOUBLEQUOTE "\""
	 */
	public static final String DQ = DOUBLEQUOTE;
	/**
	 * LESSER_THAN "<"
	 */
	public static final String LESSERTHAN = "<";
	/**
	 * LESSER_THAN "<"
	 */
	public static final String LT = LESSERTHAN;
	/**
	 * GREATER_THAN ">"
	 */
	public static final String GREATERTHAN = ">";
	/**
	 * GREATER_THAN ">"
	 */
	public static final String GT = GREATERTHAN;
	/**
	 * NEWLINE "\n"
	 */
	public static final String NEWLINE = "\n";
	/**
	 * NEWLINE "\n"
	 */
	public static final String NL = NEWLINE;
	/**
	 * ZERO "0"
	 */
	public static final String ZERO = "0";
	/**
	 * ONE "1"
	 */
	public static final String ONE = "1";
	/**
	 * HUNDRED "100"
	 */
	public static final String HUNDRED = "100";
	/**
	 * FORWARD-SLASH "/"
	 */
	public static final String SLASH = "/";
	/**
	 * FORWARD-SLASH "/"
	 */
	public static final String FORWARDSLASH = SLASH;
	/**
	 * FORWARD-SLASH "/"
	 */
	public static final String FS = SLASH;
	/**
	 * DOUBLE_SLASH "//"
	 */
	public static final String DOUBLE_SLASH = "//";
	/**
	 * EQUALS "="
	 */
	public static final String EQUALS = "=";
	/**
	 * EQUALS "="
	 */
	public static final String EQ = EQUALS;
	/**
	 * SPACE " "
	 */
	public static final String SPACE = " ";
	/**
	 * SPACE " "
	 */
	public static final String SP = SPACE;
	/**
	 * SPACE " "
	 */
	public static final String WHITESPACE = SPACE;
	/**
	 * TILDE <span style="color: Purple; font-weight:bold;">{@value}</span>
	 */
	public static final String TILDE = "~";
	/**
	 * NotApplicable <span style="color: Purple; font-weight:bold;">{@value}</span>
	 */
	public static final String N_A = "N/A";
	/**
	 * ALL <span style="color: Purple; font-weight:bold;">{@value}</span>
	 */
	public static final String ALL = "ALL";
	
	/**
	 * All <span style="color: Purple; font-weight:bold;">{@value}</span>
	 */
	public static final String All = "All";
	/**
	 * Query range
	 */
	public static final String SCHDLE_SEQ_RANGE = "1-999";
	/**
	 * SINGLEQUOTE "'"
	 */
	public static final String SINGLEQUOTE = "'";
	/**
	 * SINGLEQUOTE "'"
	 */
	public static final String SQ = SINGLEQUOTE;
	/**
	 * HASH "'"
	 */
	public static final String HASH = "#";
	
	
	public static final String DOUBLE_AT = "@@";
	
	
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";
	
	public static final String OF = " of ";
	public static final String DOLLAR ="$";
	public static final String EXCEL_EXTN =".xls";
	public static final String DOUBLE_SLASH_DOLLAR ="\\$";
	
	
	/**
	 * Returns <b>true</b> if String is null or has length equal to zero Eg.<br>
	 * <ul>
	 * 	<li>{@code null} =&gt; <b><span style="color:green;">true</span></b></li>
	 * 	<li>{@code ""} =&gt; <b><span style="color:green;">true</span></b></li>
	 * 	<li>{@code " "} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * 	<li>{@code " a"} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * </ul>
	 * 
	 * @param str String
	 * @return <b>boolean</b> String is null or has length equal to zero
	 * 
	 * @see #isNotEmpty(String) isNotEmpty()
	 * @see #is(String) is()
	 * @see #isnt(String) isnt()
	 */
	public static boolean isEmpty(String str) {
		return str==null?true:(str.length()==0);
	}
	
	/**
	 * Returns <b>true</b> if String has length greater than zero<br>
	 * <ul>
	 * 	<li>{@code null} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * 	<li>{@code ""} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * 	<li>{@code " "} =&gt; <b><span style="color:green;">true</span></b></li>
	 * 	<li>{@code " a"} =&gt; <b><span style="color:green;">true</span></b></li>
	 * </ul>
	 * 
	 * @param str String
	 * @return <b>boolean</b> String has length greater than zero
	 * 
	 * @see #isEmpty(String) isEmpty()
	 * @see #is(String) is()
	 * @see #isnt(String) isnt()
	 */
	public static boolean isNotEmpty(String str) {
		return str==null?false:(str.length()>0);
	}
	
	/**
	 * Returns <b>true</b> if String contains at least one non-whitespace
	 * character
	 * 
	 * @param str String
	 * @return <b>boolean</b> String contains at least one non-whitespace character
	 */
	public static boolean containsNonWhiteSpaceChar(String str) {
		if (str==null || str.length()==0) {
			return false;
		}
		int strLen = str.length();
		for (int i=0; i<strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) return true;
		}
		return false;
	}
	
	/**
	 * Returns <b>true</b> if String has some content, i.e., at least one non-whitespace
	 * character. Returns <b>false</b> for every other scenario. Eg.<br>
	 * <ul>
	 * 	<li>{@code null} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * 	<li>{@code ""} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * 	<li>{@code " "} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * 	<li>{@code " a"} =&gt; <b><span style="color:green;">true</span></b></li>
	 * </ul>
	 * 
	 * @param str String
	 * @return <b>boolean</b> String has some content, i.e., at least one non-whitespace
	 * character
	 * 
	 * @see #isnt(String) isnt()
	 * @see #isNotEmpty(String) isNotEmpty()
	 * @see #isEmpty(String) isEmpty()
	 */
	public static boolean is(String str) {
		return containsNonWhiteSpaceChar(str);
	}
	
	/**
	 * Returns <b>true</b> if String doesn't have content, i.e., doesn't contain even one
	 * non-whitespace character. Returns <b>false</b> for every other scenario. Eg.<br>
	 * <ul>
	 * 	<li>{@code null} =&gt; <b><span style="color:green;">true</span></b></li>
	 * 	<li>{@code ""} =&gt; <b><span style="color:green;">true</span></b></li>
	 * 	<li>{@code " "} =&gt; <b><span style="color:green;">true</span></b></li>
	 * 	<li>{@code " a"} =&gt; <b><span style="color:brown;">false</span></b></li>
	 * </ul>
	 * 
	 * @param str String
	 * @return <b>boolean</b> String doesn't have content, i.e., doesn't contain even one
	 * non-whitespace character
	 * 
	 * @see #is(String) is()
	 * @see #isNotEmpty(String) isNotEmpty()
	 * @see #isEmpty(String) isEmpty()
	 */
	public static boolean isnt(String str) {
		return !containsNonWhiteSpaceChar(str);
	}
	
	/**
	 * Returns <b>Not Null</b> String
	 * 
	 * @param str String
	 * @return <b>String</b> Not null String
	 */
	public static String nn(String str) {
		return str!=null?str:MT;
	}
	
	public static String getNotNullTrim(String str) {
		return getNotNullTrim(str, EMPTY);
	}
	
	public static String getNotNullTrim(String str, String defaultStr) {
		return str==null?defaultStr:str.trim();
	}

	public static String getYesNoEmpty(String yesNo) {
		return isYes(yesNo)?YES:(isNo(yesNo)?NO:EMPTY);
	}
	
	public static String getYesNo(boolean yesNo) {
		return (yesNo)?YES:NO;
	}

	public static String getYes(boolean yesNo) {
		return (yesNo)?YES:EMPTY;
	}
	
	public static String getNo(boolean yesNo) {
		return (!yesNo)?NO:EMPTY;
	}
	
	public static String getYN(boolean yesNo) {
		return (yesNo)?Y:N;
	}

	public static String getY(boolean yesNo) {
		return (yesNo)?Y:EMPTY;
	}
	
	public static String getN(boolean yesNo) {
		return (!yesNo)?N:EMPTY;
	}

	public static int getStringSizeNullSafe(String str) {
		return (str==null)?0:str.trim().length();
	}
	
	public static StringBuilder QT(StringBuilder sb, Object value) {
		return sb.append(DQ).append(esc(value)).append(DQ);
	}
	
	public static String QT(Object value) {
		return QT(new StringBuilder(), value).toString();
	}
	
	public static String esc(Object value) {
		return (value==null)?EMPTY:StringEscapeUtils.escapeXml10(value.toString());
	}
	
	public static String esc(String str) {
		return (str==null)?EMPTY:StringEscapeUtils.escapeXml10(str);
	}
	
	/**
	 * Returns <b>true</b> if Array is not null and has size greater than zero<br>
	 * <br>
	 * {@code arr!=null && arr.length>0}
	 * 
	 * @param arr Array object or null
	 * @return <b>boolean</b> Array is not null and has length greater than zero
	 */
	public static <E> boolean is(E[] arr) {
		return arr!=null && arr.length>0;
	}
	
	/**
	 * Returns <b>true</b> if Array is null or has size equal to zero<br>
	 * <br>
	 * {@code !(arr!=null && arr.length>0)}
	 * 
	 * @param coll Array object or null
	 * @return <b>boolean</b> Array is null or has length equal to zero
	 */
	public static <E> boolean isnt(E[] arr) {
		return !is(arr);
	}
	
	/**
	 * Returns <b>true</b> if Collection is not null and has size greater than zero<br>
	 * <br>
	 * {@code coll!=null && coll.size()>0}
	 * 
	 * @param coll Collection object or null
	 * @return <b>boolean</b> Collection is not null and has size greater than zero
	 */
	public static boolean is(Collection<?> coll) {
		return coll!=null && coll.size()>0;
	}
	
	/**
	 * Returns <b>true</b> if Collection is null or has size equal to zero<br>
	 * <br>
	 * {@code !(coll!=null && coll.size()>0)}
	 * 
	 * @param coll Collection object or null
	 * @return <b>boolean</b> Collection is null or has size equal to zero
	 */
	public static boolean isnt(Collection<?> coll) {
		return !is(coll);
	}
	
	/**
	 * Returns <b>true</b> if Map is not null and has size greater than zero<br>
	 * <br>
	 * {@code map!=null && map.size()>0}
	 * 
	 * @param map Map object or null
	 * @return <b>boolean</b> Map is not null and has size greater than zero
	 */
	public static boolean is(Map<?, ?> map) {
		return map!=null && map.size()>0;
	}
	
	/**
	 * Returns <b>true</b> if Map is null or has size equal to zero<br>
	 * <br>
	 * {@code !(map!=null && map.size()>0)}
	 * 
	 * @param map Map object or null
	 * @return <b>boolean</b> Map is null or has size equal to zero
	 */
	public static boolean isnt(Map<?, ?> map) {
		return !is(map);
	}
	
	/**
	 * Utility method to check whether an attribute is <b>YES</b>. Matches string to "y", 
	 * "true" or "yes", ignoring case. Everything else is considered not matching
	 * 
	 * @param attribtext Attribute-text
	 * @return <b>boolean</b> True or False
	 */
	public static boolean isYes(String attribtext) {
		if (attribtext==null) {
			return false;
		}
		String attrib = attribtext.trim();
		return ("y".equalsIgnoreCase(attrib) || "true".equalsIgnoreCase(attrib) ||
				"yes".equalsIgnoreCase(attrib));
	}
	
	/**
	 * Utility method to check whether an attribute is <b>NO</b>. Matches string to "n", 
	 * "false" or "no", ignoring case. Everything else is considered not matching
	 * 
	 * @param attribtext Attribute-text
	 * @return <b>boolean</b> True or False
	 */
	public static boolean isNo(String attribtext) {
		if (attribtext==null) {
			return false;
		}
		String attrib = attribtext.trim();
		return ("n".equalsIgnoreCase(attrib) || "false".equalsIgnoreCase(attrib) ||
				"no".equalsIgnoreCase(attrib));
	}
}
