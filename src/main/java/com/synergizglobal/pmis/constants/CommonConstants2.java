package com.synergizglobal.pmis.constants;

import java.io.File;

public class CommonConstants2 {	
	
	public final static String  STATUS_COMPLETED = "Completed";
	public final static String  STATUS_DROPPED = "Dropped";	
	public final static String  STATUS_IN_PROGRESS = "In Progress";
	public final static String  STATUS_NOT_STARTED = "Not Started";
	public final static String  STATUS_ON_HOLD = "On Hold";
	
	public static final String USER_IMAGES = "/pmis/USER_IMAGES/";
	public static final String CONTRACT_FILES = "/pmis/CONTRACT_FILES/";
	public static final String FOB_FILES = "/pmis/FOB_FILES/";
	public static final String ISSUE_FILES = "/pmis/ISSUE_FILES/";
	public static final String SAFETY_FILES = "/pmis/SAFETY_FILES/";
	public static final String WORK_FILES = "/pmis/WORK_FILES/";
	public static final String DESIGN_FILES = "/pmis/DESIGN_FILES/";
	public static final String P6_FILES = "/pmis/P6_FILES/";
	
	/*********** LOCAL ****************************************************/
	
	public final static String ISSUE_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"ISSUE_FILES"+File.separator;
	public static final String SAFETY_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"SAFETY_FILES"+File.separator;
	public static final String FOB_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"FOB_FILES"+File.separator;
	public static final String USER_IMAGE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"USER_IMAGES"+File.separator;
	public static final String DESIGN_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"DESIGN_FILES"+File.separator;
	public static final String P6_FILE_SAVING_PATH = "E:"+File.separator+"Development"+File.separator+"nginx-1.9.9"+File.separator+"html"+File.separator+"pmis"+File.separator+"P6_FILES"+File.separator;
	
	/*********** PRODUCTION ****************************************************/
	
	/*public final static String ISSUE_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/ISSUE_FILES/";
	public final static String SAFETY_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/SAFETY_FILES/";
	public final static String FOB_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/FOB_FILES/";
	public final static String USER_IMAGE_SAVING_PATH = "usr/share/nginx/html/pmis/USER_IMAGES/";
	public final static String DESIGN_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/USER_IMAGES/";
	public final static String P6_FILE_SAVING_PATH = "usr/share/nginx/html/pmis/P6_FILES/";*/
}
