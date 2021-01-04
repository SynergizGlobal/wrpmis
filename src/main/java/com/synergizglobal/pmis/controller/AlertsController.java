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

import com.synergizglobal.pmis.Iservice.AlertsService;

@Controller
public class AlertsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(AlertsController.class);
	
	@Autowired
	AlertsService service;
	
	@Value("${common.error.message}")
	public String commonError;
	
	//@Scheduled(cron = "0 0/3 * * * *")	//  = every minute.
	@Scheduled(cron = "0 2 1 ? * *")	//  = every day.
	public void generateAtertsByCronJob(){		
	     String message = "Method executed every day at 02:01 am. Current time is :: "+ new Date();
	     
	     logger.error("generateDPRReportsByCronJob : "+message);	    
	     try {
	    	 
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAtertsByCronJob();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAtertsByCronJob : "+flag);
	    	 
			flag = service.sendMailAlerts();
			logger.error("generateAtertsByCronJob >> sendMailAlerts >> Sending mails : "+ flag); 
			
		    //flag = service.sendNotificationAlertMails();
		    //logger.error("generateAtertsByCronJob >> sendNotificationAlertMails >> Sending mails : "+ flag); 
		    //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAtertsByCronJob() : "+e.getMessage());
		 }
	}
}
