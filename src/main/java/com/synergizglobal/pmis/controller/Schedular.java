package com.synergizglobal.pmis.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.synergizglobal.pmis.Iservice.HomeService;

@Controller
public class Schedular {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(AlertsController.class);
	
	@Autowired
	HomeService service;
	
	@Value("${common.error.message}")
	public String commonError;
	
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	@Scheduled(cron = "${cron.expression.user.login.timeout}")
	public void userLoginTimeout(){
	     try {
	    	 logger.error("userLoginTimeout : Method executed at > "+new Date());
             boolean flag = service.userLoginTimeout();
	    	 logger.error("userLoginTimeout : "+flag);
		 } catch (Exception e) {
			 e.printStackTrace();
			 logger.error("userLoginTimeout() : "+e.getMessage());
		 }
	}
}
