package com.synergizglobal.pmis.common;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.synergizglobal.pmis.constants.CommonConstants;

public class UrlGenerator {
	public static String getURLBase() throws MalformedURLException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
		        .getRequestAttributes()).getRequest();
	    URL requestURL = new URL(request.getRequestURL().toString());
	    String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
	    //return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	    
	    //return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	    
	    //return CommonConstants.PORTAL + request.getContextPath();
	    return CommonConstants.CONTEXT_PATH;
	}
}
