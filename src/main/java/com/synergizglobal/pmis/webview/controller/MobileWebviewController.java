package com.synergizglobal.pmis.webview.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mobileappwebview")
public class MobileWebviewController {
	Logger logger = Logger.getLogger(MobileWebviewController.class);
	
	@Value("${common.error.message}")
	public String commonError;
	
	/**
	 * This method risk() will execute when user request the for Risk module.
	 * 
	 * @param obj is object for the model class Risk. 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView risk(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("risk : " + e.getMessage());
		}
		return model;
	}
	
}
