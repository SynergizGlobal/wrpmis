package com.synergizglobal.pmis.constants;

import java.io.File;

public class CommonConstants {
	public final static String BASE_URL = "https://infoviz.syntrackpro.com/trusted/{0}/";  //{0} is the placeholder for tableau trusted token
	public final static String TABLEAU_PARAMS = "&:tabs=no&:toolbar=no";
	
	
	public final static int  RANDOM_NUMERIC_NUMBER_LENGTH = 5;
	
	public final static String  ADMIN_ROLE_NAME = "Admin";
	public final static String  ACTIVE = "1";
	public final static String  INACTIVE = "2";
	
	/*********** LOCAL ****************************************************/
	
	public final static String LOGIN_BACKGROUND_IMAGE = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"resources"+File.separator+"images"+File.separator;
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://localhost/pmis";
	public final static String STRIPCHART_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"STRIPCHART_FILES"+File.separator;
	
	/*********** PRODUCTION ****************************************************/
	
	/*public final static String LOGIN_BACKGROUND_IMAGE = "usr/share/nginx/html/pmis/resources/images/";
	public static String LOGIN_BACKGROUND_IMAGE_URL = "/pmis/resources/images/login-background.jpg";
	public final static String CONTEXT_PATH = "http://syntrackpro.com/pmis";
	public final static String STRIPCHART_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/STRIPCHART_FILES/";*/
	
}
