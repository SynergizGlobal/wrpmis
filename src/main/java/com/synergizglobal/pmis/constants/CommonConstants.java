package com.synergizglobal.pmis.constants;

import java.io.File;

import com.synergizglobal.pmis.common.UrlGenerator;

public class CommonConstants {
	
	UrlGenerator ugObj = new UrlGenerator();
	
	public String BASE_URL = "https://infoviz.syntrackpro.com/trusted/{0}/";  //{0} is the placeholder for tableau trusted token
	//public String BASE_URL = "http://"+ugObj.getIpAddress()+":8000/trusted/{0}/";  //{0} is the placeholder for tableau trusted token
	public final static String TABLEAU_PARAMS = "&:tabs=no&:toolbar=no";
	
	
	public final static int  RANDOM_NUMERIC_NUMBER_LENGTH = 5;
	
	public final static String  BCC_MAIL = "support_pmis@mrvc.gov.in";
	
	public final static String  ROLE_CODE_DATA_ADMIN = "DA";
	public final static String  ROLE_CODE_INPUT_USER = "IU";
	public final static String  ROLE_CODE_IT_ADMIN = "IT";
	public final static String  ROLE_CODE_REGULAR_USER = "RU";
	public final static String  ROLE_CODE_SUPER_USER = "SU";
	
	public final static String  ACTIVE = "Active";
	public final static String  INACTIVE = "Inactive";
	
	public final static String  YES = "Yes";
	public final static String  USER = "Synergiz";
	public final static String  USER_TYPE = "HOD";
	public final static String  USER_TYPE2 = "DyHOD";
	
	public final static String  USER_TYPE_HOD = "HOD";
	public final static String  USER_TYPE_DYHOD = "DyHOD";
	public final static String  USER_TYPE_MANAGEMENT = "Management";
	public final static String  USER_TYPE_OTHERS = "Others";
	public final static String  USER_TYPE_ID_MANAGEMENT = "MGMT";
	
	public final static String  ISSUE_STATUS_RAISED = "Raised";
	
	public final static String SAFETY_EQUIPMENT_FILES = "/pmis/SAFETY_EQUIPMENT_FILES/";
	public final static String BUDGET_FILES = "/pmis/BUDGET_FILES/";
	public final static String FUND_FILES = "/pmis/FUND_FILES/";
	public final static String DOCUMENT_FILES = "/pmis/DOCUMENT_FILES/";
	public final static String SAFETY_FILES = "/pmis/SAFETY_FILES/";
	public final static String SAFETY_INSTRUCTIONS_FILES = "/pmis/SAFETY_INSTRUCTIONS_FILES/";
	public final static String MANUAL_FILES = "/pmis/MANUAL_FILES/";
	public static final String PROJECT_FILES = "/pmis/PROJECT_FILES/";
	public static final String WORK_FILES = "/pmis/WORK_FILES/";
	public static final String DELIVERABLES_FILES = "/pmis/DELIVERABLES_FILES/";
	public static final String TRAINING_SESSIONS = "/pmis/TRAINING_SESSIONS/";
	public static final String LAND_ACQUISITION_FILES = "/pmis/LAND_ACQUISITION_FILES/";
	public final static String DESIGN_UPLOADED_FILES = "/pmis/DESIGN_UPLOADED_FILES/";
	public final static String TEMPLATE_FILE_SAVE_PATH = "/pmis/TEMPLATES_OLD/";
	
	/*********** LOCAL ****************************************************/
	
	public static String DIRECTORY_PATH = "E:";
	
	
	public final static String LOGIN_BACKGROUND_IMAGE = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"resources"+File.separator+"images"+File.separator;
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://localhost/pmis";
	public final static String STRIPCHART_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"STRIPCHART_FILES"+File.separator;
	public final static String WORK_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"WORK_FILES"+File.separator;
	public final static String CONTRACT_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"CONTRACT_FILES"+File.separator;
	public final static String DESIGN_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DESIGN_FILES"+File.separator;
	public final static String SAFETYEQUIPMENT_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_EQUIPMENT_FILES"+File.separator;
	public final static String BUDGET_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"BUDGET_FILES"+File.separator;
	public final static String FUND_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"FUND_FILES"+File.separator;
	public final static String DOCUMENT_FILES_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DOCUMENT_FILES"+File.separator;
	public static final String SAFETY_INSTRUCTIONS_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_INSTRUCTIONS_FILES"+File.separator;
	public final static String MANUAL_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"MANUAL_FILES"+File.separator;
	public final static String PROJECT_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"PROJECT_FILES"+File.separator;
	public final static String DELIVERABLES_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DELIVERABLES_FILES"+File.separator;
	public final static String TRAINING_SESSION_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"TRAINING_SESSIONS"+File.separator;
	public final static String LAND_ACQUISITION_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"LAND_ACQUISITION_FILES"+File.separator;
	public final static String DESIGN_UPLOADED_FILE_SAVING_PATH = DIRECTORY_PATH+File.separator+"Development/nginx-1.9.9/html/pmis/DESIGN_UPLOADED_FILES/";
	public final static String TEMPLATE_FILEPATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator;
	public final static String TEMPLATE_OLD_FILEPATH = DIRECTORY_PATH+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"TEMPLATES_OLD"+File.separator;

	/*********** Syntract AWS ****************************************************/	
	/*public final static String LOGIN_BACKGROUND_IMAGE = "usr/share/nginx/html/pmis/resources/images/";
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://syntrackpro.com/pmis";
	public final static String STRIPCHART_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/STRIPCHART_FILES/";
	public final static String WORK_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/WORK_FILES/";
	public final static String CONTRACT_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/CONTRACT_FILES/";
	public final static String DESIGN_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/DESIGN_FILES/";
	public final static String SAFETYEQUIPMENT_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/SAFETY_EQUIPMENT_FILES/";
	public final static String BUDGET_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/BUDGET_FILES/";
	public final static String FUND_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/FUND_FILES/";
	public final static String DOCUMENT_FILES_SAVING_PATH = "usr/share/nginx/html/pmis/DOCUMENT_FILES/";
	public final static String SAFETY_INSTRUCTIONS_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/SAFETY_INSTRUCTIONS_FILES/";
	public final static String MANUAL_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/MANUAL_FILES/";
	public final static String PROJECT_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/PROJECT_FILES/";
	public final static String DELIVERABLES_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/DELIVERABLES_FILES/";
	public final static String TRAINING_SESSION_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/TRAINING_SESSIONS/";
	public final static String LAND_ACQUISITION_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/LAND_ACQUISITION_FILES/";
	public final static String DESIGN_UPLOADED_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/DESIGN_UPLOADED_FILES/";
	public final static String TEMPLATE_FILEPATH = "usr/share/nginx/html/pmis/";
	public final static String TEMPLATE_OLD_FILEPATH = "usr/share/nginx/html/pmis/TEMPLATES_OLD/";
	*/
	
	/*********** MRVC Server ****************************************************/	
	/*public final static String LOGIN_BACKGROUND_IMAGE = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"resources"+File.separator+"images"+File.separator;
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://10.203.10.157/pmis";
	public final static String STRIPCHART_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"STRIPCHART_FILES"+File.separator;
	public final static String WORK_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"WORK_FILES"+File.separator;
	public final static String CONTRACT_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"CONTRACT_FILES"+File.separator;
	public final static String DESIGN_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DESIGN_FILES"+File.separator;
	public final static String SAFETYEQUIPMENT_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_EQUIPMENT_FILES"+File.separator;
	public final static String BUDGET_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"BUDGET_FILES"+File.separator;
	public final static String FUND_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"FUND_FILES"+File.separator;
	public final static String DOCUMENT_FILES_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DOCUMENT_FILES"+File.separator;
	public final static String SAFETY_INSTRUCTIONS_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_INSTRUCTIONS_FILES"+File.separator;
	public final static String MANUAL_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"MANUAL_FILES"+File.separator;
	public final static String PROJECT_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"PROJECT_FILES"+File.separator;
	public final static String DELIVERABLES_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DELIVERABLES_FILES"+File.separator;
	public final static String TRAINING_SESSION_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"TRAINING_SESSIONS"+File.separator;
	public final static String LAND_ACQUISITION_FILE_SAVING_PATH = "D:"+File.separator+"PMISApplicationServer"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"LAND_ACQUISITION_FILES"+File.separator;
	public final static String DESIGN_UPLOADED_FILE_SAVING_PATH = "D:/PMISApplicationServer/nginx-1.9.9/html/pmis/DESIGN_UPLOADED_FILES/";
	public final static String TEMPLATE_FILEPATH = "D:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator;
	public final static String TEMPLATE_OLD_FILEPATH = "D:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"TEMPLATES_OLD"+File.separator;

	*/
	
}
