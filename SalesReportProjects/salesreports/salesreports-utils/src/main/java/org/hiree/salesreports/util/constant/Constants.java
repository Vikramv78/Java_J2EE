package org.hiree.salesreports.util.constant;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Constants {
	public static final String STRING_Y ="Y";
	public static final String STRING_N ="N";
	public static  String BASE_PATH;
	public static  String WEB_INF_PATH;
	public static  String XML_CNFGN_PATH;
    public static final String XML_CNFGN = "XML_CNFGN";
    public static  String TEMP_PATH;
    public static final String EMPTY = "";
    public static final String WEB_INF = "WEB-INF";
    
    public static String SERVER_ROOT_PATH;
    public static  String ACCOUNT_SID ;
	public static  String AUTH_TOKEN;
	public static  String MESSAGING_SERVICE_SID;
	public static  String FROM_TWILIO_NUMBER;
	public static  String TASKROUTER_WORKSPACE_SID;
	public static  String TASKROUTER_WORKFLOW_SID;
	
	public static  String APPLICATION_SID;
	public static  String TASKROUTER_FROM_TWILIO_NUMBER;
	public static  String TASKROUTER_POST_WORK_ACTIVITY_SID;
	
	
	
	
	
	
	
	
	
	 
	
	private static Logger logger = LoggerFactory.getLogger(Constants.class);
	
	
	 public static String joinAsFilePath(String... params) {
		    // Could have simply used StringUtils.join - but it needs you to declare
		    // an array....
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < params.length; i++) {
		      if (null != params[i]) {
		        sb.append(params[i]);
		        if (i < params.length - 1) {
		          sb.append(File.separator);
		        }
		      }
		    }
		    logger.info(sb.toString());
		    return sb.toString();
		  }
	 
	 public static String joinAsPath(String... params) {
		    // Could have simply used StringUtils.join - but it needs you to declare
		    // an array....
		    StringBuilder sb = new StringBuilder();
		    for (String param : params) {
		      if (null != param) {
		        sb.append(param);
		        sb.append(File.separator);
		      }
		    }
		    logger.info(sb.toString());
		    return sb.toString();
		  }
}
