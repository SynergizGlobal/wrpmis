package com.synergizglobal.pmis.constants;

import java.io.File;

public class CommonConstants {
	public final static String BASE_URL = "https://infoviz.syntrackpro.com/trusted/{0}/";  //{0} is the placeholder for tableau trusted token
	public final static String TABLEAU_PARAMS = "&:tabs=no&:toolbar=no";
	
	
	public final static int  RANDOM_NUMERIC_NUMBER_LENGTH = 5;
	
	public final static String  ADMIN_ROLE_NAME = "Admin";
	public final static String  ACTIVE = "1";
	public final static String  INACTIVE = "2";
	
	public final static String  ACTIVITY_COMPLETED = "3";
	public final static String  ACTIVITY_IN_PROGRESS = "2";
	public final static String  ACTIVITY_NOT_STARTED = "1";
	
	public final static String  QUERY_TYPE_INSERT_ID = "1";
	public final static String  QUERY_TYPE_UPDATE_ID = "2";
	public final static String  QUERY_TYPE_DELETE_ID = "3";
	
	public final static String NOTIFICATIONS_URL = "/activity-update-form";
	
	/* Testing S3 bucket path*/
	/*public final static String ACTIVITY_IMAGES = "Testing/MRVC/Input_form_uploads/";
	public final static String P6_DATA_FILES = "Testing/MRVC/P6_Data_Files/";
	public final static String LOGIN_BACKGROUND_IMAGE = "Testing/MRVC";
	public static String LOGIN_BACKGROUND_IMAGE_URL = "https://syntrack-dev.s3.ap-south-1.amazonaws.com/Testing/MRVC/login-background.jpg";*/
	
	/* Development S3 bucket path*/
	/*public final static String ACTIVITY_IMAGES = "Development/MRVC/Input_form_uploads/";
	public final static String P6_DATA_FILES = "Development/MRVC/P6_Data_Files/";
	public final static String LOGIN_BACKGROUND_IMAGE = "Development/MRVC";	
	public static String LOGIN_BACKGROUND_IMAGE_URL = "https://syntrack-dev.s3.ap-south-1.amazonaws.com/Development/MRVC/login-background.jpg";*/
	
	
	/*********** LOCAL ****************************************************/
	
	public final static String ACTIVITY_IMAGES = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"activity_images"+File.separator;
	public final static String P6_DATA_FILES = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"P6_Data_Files"+File.separator;
	public final static String LOGIN_BACKGROUND_IMAGE = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"resources"+File.separator+"new"+File.separator+"images"+File.separator;
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://localhost/pmis";
	public final static String NOTIFICATIONS_EMAIL = "kallurigurappa@gmail.com,gkalluri@ghsltechnologies.com";
	
	
	public final static String RISK_ANALYSIS_REPORTS_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"RISK_ANALYSIS_REPORTS"+File.separator;
	
	public final static String RISK_ANALYSIS_REPORTS_GET_PATH = "/pmis/RISK_ANALYSIS_REPORTS/";
	
	public final static String RISKS_BULK_UPLOAD_TEMPLATE = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"RISKS_BULK_UPLOAD_TEMPLATE"+File.separator;
	public final static String RISKS_BULK_UPLOAD_FILES_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"RISKS_BULK_UPLOAD_FILES"+File.separator;
	
	/*********** QA ****************************************************/
	
	/*public final static String ACTIVITY_IMAGES = "D:"+File.separator+"TestingServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"activity_images"+File.separator;
	public final static String P6_DATA_FILES = "D:"+File.separator+"TestingServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"P6_Data_Files"+File.separator;
	public final static String LOGIN_BACKGROUND_IMAGE = "D:"+File.separator+"TestingServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"mrvc"+File.separator+"resources"+File.separator+"new"+File.separator+"images"+File.separator;
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://183.82.105.133/mrvc";
	public final static String NOTIFICATIONS_EMAIL = "vikramaditya.s@synergizglobal.com,gkalluri@ghsltechnologies.com";*/
	
	/*********** PRODUCTION ****************************************************/
	
	/*public final static String ACTIVITY_IMAGES = "usr/share/nginx/html/pmis/activity_images/";
	public final static String P6_DATA_FILES = "usr/share/nginx/html/pmis/P6_Data_Files/";
	public final static String LOGIN_BACKGROUND_IMAGE = "usr/share/nginx/html/pmis/resources/images/";
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://syntrackpro.com/mrvc";
	public final static String NOTIFICATIONS_EMAIL = "rajiv.dhupkar@synergizglobal.com,suresh.r@synergizglobal.com,raviteja.reddy@synergizglobal.com,vikramaditya.s@synergizglobal.com";
	public final static String RISK_ANALYSIS_REPORTS_SAVING_PATH = "usr/share/nginx/html/pmis/RISK_ANALYSIS_REPORTS/";
	public final static String RISK_ANALYSIS_REPORTS_GET_PATH = "/pmis/RISK_ANALYSIS_REPORTS/";
	public final static String RISKS_BULK_UPLOAD_TEMPLATE = "usr/share/nginx/html/pmis/RISKS_BULK_UPLOAD_TEMPLATE/";
	public final static String RISKS_BULK_UPLOAD_FILES_SAVING_PATH = "usr/share/nginx/html/pmis/RISKS_BULK_UPLOAD_FILES/";*/
}
