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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.model.Issue;

@Controller
public class Schedular {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(Schedular.class);
	
	@Autowired
	HomeService service;
	
	@Autowired
	IssuesReportController issueReportController;
	
	@Value("${common.error.message}")
	public String commonError;
	
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	@Scheduled(cron = "${cron.expression.user.login.timeout}")
	public void userLoginTimeout(){
	     try {
	    	 //logger.error("userLoginTimeout : Method executed at > "+new Date());
             boolean flag = service.userLoginTimeout();
	    	 //logger.error("userLoginTimeout : "+flag);
		 } catch (Exception e) {
			 e.printStackTrace();
			 logger.error("userLoginTimeout() : "+e.getMessage());
		 }
	}
	
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	@Scheduled(cron = "${cron.expression.send.mail.with.open.issues}")
	public void sendMailWithOpenIssues(){
	     try {
	    	 logger.error("sendMailWithOpenIssues : Method executed at > "+new Date());
	    	 Issue obj = new Issue();
             boolean flag = issueReportController.sendMailWithOpenIssues(obj);
	    	 logger.error("sendMailWithOpenIssues : "+flag);
		 } catch (Exception e) {
			 e.printStackTrace();
			 logger.error("sendMailWithOpenIssues() : "+e.getMessage());
		 }
	}
}
