package com.synergizglobal.pmis.constants;

import java.io.File;

public class CommonConstants {
	//public final static String BASE_URL = "https://infoviz.syntrackpro.com/trusted/{0}/";  //{0} is the placeholder for tableau trusted token
	public final static String BASE_URL = "http://10.203.10.157:8000/trusted/{0}/";  //{0} is the placeholder for tableau trusted token
	public final static String TABLEAU_PARAMS = "&:tabs=no&:toolbar=no";
	
	
	public final static int  RANDOM_NUMERIC_NUMBER_LENGTH = 5;
	
	public final static String  ADMIN_ROLE_NAME = "Admin";
	public final static String  ACTIVE = "Active";
	public final static String  INACTIVE = "Inactive";
	
	public final static String SAFETY_EQUIPMENT_FILES = "/pmis/SAFETY_EQUIPMENT_FILES/";
	public final static String BUDGET_FILES = "/pmis/BUDGET_FILES/";
	public final static String FUND_FILES = "/pmis/FUND_FILES/";
	public final static String DOCUMENT_FILES = "/pmis/DOCUMENT_FILES/";
	
	/*********** LOCAL ****************************************************/
	
	public final static String LOGIN_BACKGROUND_IMAGE = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"resources"+File.separator+"images"+File.separator;
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://localhost/pmis";
	public final static String STRIPCHART_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"STRIPCHART_FILES"+File.separator;
	public final static String WORK_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"WORK_FILES"+File.separator;
	public final static String CONTRACT_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"CONTRACT_FILES"+File.separator;
	public final static String DESIGN_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DESIGN_FILES"+File.separator;
	public final static String SAFETYEQUIPMENT_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_EQUIPMENT_FILES"+File.separator;
	public final static String BUDGET_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"BUDGET_FILES"+File.separator;
	public final static String FUND_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"FUND_FILES"+File.separator;
	public final static String DOCUMENT_FILES_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DOCUMENT_FILES"+File.separator;
	
	
	/*********** PRODUCTION ****************************************************/
	
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
	public final static String DOCUMENT_FILES_SAVING_PATH = "usr/share/nginx/html/pmis/DOCUMENT_FILES/";*/
	
	
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
	
	*/
	
}
