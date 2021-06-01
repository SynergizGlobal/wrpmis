package com.synergizglobal.pmis.constants;

import java.io.File;

public class CommonConstants2 {	
	
	public final static String  STATUS_COMPLETED = "Completed";
	public final static String  STATUS_DROPPED = "Dropped";	
	public final static String  STATUS_IN_PROGRESS = "In Progress";
	public final static String  STATUS_NOT_STARTED = "Not Started";
	public final static String  STATUS_ON_HOLD = "On Hold";
	
	public final static String  LOGIN_EVENT_TYPE_LOGIN = "Login";
	public final static String  LOGIN_EVENT_TYPE_LOGOUT = "Logout";
	
	public final static String  LOGOUT_TYPE_TIMEOUT = "Timeout";
	public final static String  LOGOUT_TYPE_LOGOUT = "Logout";
	
	public final static String  ALERT_TYPE_BANK_GUARANTEE = "Bank Guarantee";
	public final static String  ALERT_TYPE_CONTRACT_PERIOD = "Contract Period";
	public final static String  ALERT_TYPE_CONTRACT_VALUE = "Contract Value";
	public final static String  ALERT_TYPE_INSURANCE = "Insurance";
	public final static String  ALERT_TYPE_ISSUE = "Issue";
	public final static String  ALERT_TYPE_PHYSICAL_PROGRESS = "Physical Progress";
	public final static String  ALERT_TYPE_RISK = "Risk";
	public final static String  ALERT_TYPE_SAFETY = "Safety";
	
	public static final String USER_IMAGES = "/pmis/USER_IMAGES/";
	public static final String CONTRACT_FILES = "/pmis/CONTRACT_FILES/";
	public static final String FOB_GALLERY = "/pmis/FOB_GALLERY/";
	public static final String ISSUE_FILES = "/pmis/ISSUE_FILES/";
	public static final String SAFETY_FILES = "/pmis/SAFETY_FILES/";
	public static final String WORK_FILES = "/pmis/WORK_FILES/";
	public static final String DESIGN_FILES = "/pmis/DESIGN_FILES/";
	public static final String P6_FILES = "/pmis/P6_FILES/";
	public static final String STRIPCHART_FILES = "/pmis/STRIPCHART_FILES/";
	public final static String WEB_DOCUMENTS = "/pmis/WEB_DOCUMENTS/";
	public final static String PROJECT_GALLERY = "/pmis/PROJECT_GALLERY/";	
	public final static String ACTIVITY_PROGRESS_REPORT = "/pmis/ACTIVITY_PROGRESS_REPORTS/";
	public final static String ACTIVITIES_UPLOAD_FILES = "/pmis/ACTIVITIES_UPLOAD_FILES/";
	public final static String RISK_ASSESSMENT_UPLOADED_FILES = "/pmis/RISK_ASSESSMENT_UPLOADED_FILES/";
	
	
	
	/*********** LOCAL ****************************************************/
	public static String DIRECTORY_PATH = "E:";
	
	public final static String USER_LOGIN_REPORT_MAIL = "gkalluri@ghsltechnologies.com";
	public final static String ALERTS_EMAIL = "kallurigurappa@gmail.com,gkalluri@ghsltechnologies.com";
	public final static String ISSUE_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"ISSUE_FILES"+File.separator;
	public static final String SAFETY_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_FILES"+File.separator;
	public static final String FOB_GALLERY_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"FOB_GALLERY"+File.separator;
	public static final String USER_IMAGE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"USER_IMAGES"+File.separator;
	public static final String DESIGN_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DESIGN_FILES"+File.separator;
	public static final String P6_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"P6_FILES"+File.separator;
	public static final String DOCX_LOGO = DIRECTORY_PATH + "/Development/nginx-1.9.9/html/pmis/resources/images";
	public final static String WEB_DOCUMENTS_FILE_SAVING_PATH = DIRECTORY_PATH + "/Development/nginx-1.9.9/html/pmis/WEB_DOCUMENTS/";
	public final static String PROJECT_GALLERY_FILE_SAVING_PATH = DIRECTORY_PATH + "/Development/nginx-1.9.9/html/pmis/PROJECT_GALLERY/";
	//public final static String ACTIVITY_PROGRESS_FILE_SAVING_PATH = DIRECTORY_PATH + "/Development/nginx-1.9.9/html/pmis/ACTIVITY_PROGRESS_REPORTS/";
	public final static String ACTIVITIES_UPLOAD_FILE_SAVING_PATH = DIRECTORY_PATH + "/Development/nginx-1.9.9/html/pmis/ACTIVITIES_UPLOAD_FILES/";
	public final static String RISK_ASSESSMENT_UPLOADED_FILE_SAVING_PATH = DIRECTORY_PATH + "/Development/nginx-1.9.9/html/pmis/RISK_ASSESSMENT_UPLOADED_FILES/";
	
	/*********** PRODUCTION ****************************************************/
	
	/*public final static String USER_LOGIN_REPORT_MAIL = "raviteja.reddy@synergizglobal.com";
	public final static String ALERTS_EMAIL = "rajiv.dhupkar@synergizglobal.com,raviteja.reddy@synergizglobal.com";
	public final static String ISSUE_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/ISSUE_FILES/";
	public final static String SAFETY_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/SAFETY_FILES/";
	public final static String FOB_GALLERY_SAVING_PATH = "usr/share/nginx/html/pmis/FOB_GALLERY/";
	public final static String USER_IMAGE_SAVING_PATH = "usr/share/nginx/html/pmis/USER_IMAGES/";
	public final static String DESIGN_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/DESIGN_FILES/";
	public final static String P6_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/P6_FILES/";
	public static final String DOCX_LOGO = "usr/share/nginx/html/pmis/resources/images";
	public final static String WEB_DOCUMENTS_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/WEB_DOCUMENTS/";
	public final static String PROJECT_GALLERY_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/PROJECT_GALLERY/";
	public final static String ACTIVITY_PROGRESS_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/ACTIVITY_PROGRESS_REPORTS/";
	public final static String ACTIVITIES_UPLOAD_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/ACTIVITIES_UPLOAD_FILES/";
	public final static String RISK_ASSESSMENT_UPLOADED_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/RISK_ASSESSMENT_UPLOADED_FILES/";*/
	
	/* public final static String USER_LOGIN_REPORT_MAIL = "dycste2@mrvc.gov.in";
	public final static String ALERTS_EMAIL = "rajiv.dhupkar@synergizglobal.com,raviteja.reddy@synergizglobal.com";
	public final static String ISSUE_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"ISSUE_FILES"+File.separator;
	public static final String SAFETY_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_FILES"+File.separator;
	public static final String FOB_GALLERY_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"FOB_GALLERY"+File.separator;
	public static final String USER_IMAGE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"USER_IMAGES"+File.separator;
	public static final String DESIGN_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DESIGN_FILES"+File.separator;
	public static final String P6_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"P6_FILES"+File.separator;
	public static final String DOCX_LOGO = "D:/PMISApplicationServer/nginx-1.9.9/html/pmis/resources/images";
	public final static String WEB_DOCUMENTS_FILE_SAVING_PATH = "D:/PMISApplicationServer/nginx-1.9.9/html/pmis/WEB_DOCUMENTS/";
	public final static String PROJECT_GALLERY_FILE_SAVING_PATH = "D:/PMISApplicationServer/nginx-1.9.9/html/pmis/PROJECT_GALLERY/";
	public final static String ACTIVITY_PROGRESS_FILE_SAVING_PATH = "D:/PMISApplicationServer/nginx-1.9.9/html/pmis/ACTIVITY_PROGRESS_REPORTS/";
	public final static String ACTIVITIES_UPLOAD_FILE_SAVING_PATH = "D:/PMISApplicationServer/nginx-1.9.9/html/pmis/ACTIVITIES_UPLOAD_FILES/";
	public final static String RISK_ASSESSMENT_UPLOADED_FILE_SAVING_PATH = "D:/PMISApplicationServer/nginx-1.9.9/html/pmis/RISK_ASSESSMENT_UPLOADED_FILES/";
	*/
	
}
