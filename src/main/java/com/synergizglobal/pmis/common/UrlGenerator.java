package com.synergizglobal.pmis.common;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UrlGenerator {
	Logger logger = Logger.getLogger(UrlGenerator.class);
	public String getURLBase() {
		String base_url = "";
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			        .getRequestAttributes()).getRequest();
		    URL requestURL = new URL(request.getRequestURL().toString());
		    String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
		    //return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
		    
		    base_url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		    
		    //return CommonConstants.PORTAL + request.getContextPath();
		    //return CommonConstants.CONTEXT_PATH;
		}catch (Exception e) {
			logger.error("getURLBase : " + e.getMessage());
		}
		return base_url;
	}
	
	public String getIpAddress(){
		String ipAddress = "";
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			        .getRequestAttributes()).getRequest();
			
		    URL requestURL = new URL(request.getRequestURL().toString());
		    String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
		    //return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
		    //ipAddress = requestURL.getHost();
		    ipAddress = request.getServerName().toString();
			/*if("10.203.10.157".equals(ipAddress)) {
				ipAddress = "pmis.mrvc.gov.in";
			}*/
		} catch (Exception e) {
			logger.error("getIpAddress : " + e.getMessage());
		}
		return ipAddress;
	}
	
	public String getContextPath(){
		String context_path = "";
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			        .getRequestAttributes()).getRequest();
		    context_path = request.getContextPath().toString();
		    if(!StringUtils.isEmpty(context_path)) {
		    	context_path = context_path.replaceAll("/", "");
		    }
		} catch (Exception e) {
			logger.error("getContextPath : " + e.getMessage());
		}
		return context_path;
	}
}
