package com.synergizglobal.wrpmis.common;

import java.util.Arrays;
import java.util.List;

public class PermissionURLs {
	static UrlGenerator ugObj = new UrlGenerator();
	static String context_path = ugObj.getContextPath();
	public static List<String> urls = Arrays.asList(
	    "/"+context_path+"/",
	    "/",
	    "/"+context_path+"/login",
	    "/login",
	    "/"+context_path+"/logout",
	    "/logout",
	    "/"+context_path+"/home",
	    "/home",
	    "/"+context_path+"/home-new",
	    "/home-new",
	    "/"+context_path+"/new-home",
	    "/new-home",
	    "/"+context_path+"/project-overview",
	    "/project-overview",
	    "/"+context_path+"/profile",
	    "/profile",
	    "/"+context_path+"/update-profile",
	    "/update-profile",
	    "/"+context_path+"/reset-password",
	    "/reset-password",
	    "/"+context_path+"/change-password",
	    "/change-password",
	    "/update-user-manual", 
	    "/add-user-manual", 
	    "/delete-user-manual", 
	    "/user-manuals", 
	    "/auto-responsibility"
	);
}
