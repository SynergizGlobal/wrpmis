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
import org.springframework.web.servlet.ModelAndView;

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
	
	@Value("${cron.expression}")
	public String cronExpression;
	
	//@Scheduled(cron = "0 0/3 * * * *")	//  = every minute.
	//@Scheduled(cron = "0 50 10 * * *")	//  = every day 2:10 am.
	//@Scheduled(cron = "0 10 16 * * *")	//  = every day 4:10 pm.
	@Scheduled(cron = "${cron.expression}")
	public void generateAlertsByCronJob(){		
	     String message = "Method executed every day at 02:01 am. Current time is :: "+ new Date();
	     
	     logger.error("generateAlertsByCronJob : "+message);	    
	     try {
	    	 
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAtertsByCronJob();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAlertsByCronJob : "+flag);
			
		    flag = service.sendNotificationAlertMails();
		    logger.error("generateAtertsByCronJob >> sendNotificationAlertMails >> Sending mails : "+ flag); 
		    //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByCronJob() : "+e.getMessage());
		 }
	}
	
	@RequestMapping(value="/generate-and-send-alerts-to-all",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAndSendAlertsToAllByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/home");	    
	     try {
	    	logger.error("generateAndSendAlertsToAllByManual : start");
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAtertsByCronJob();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAndSendAlertsToAllByManual : "+flag);
			
		    flag = service.sendNotificationAlertMails();
		    logger.error("generateAndSendAlertsToAllByManual >> Sending mails : "+ flag); 
		    //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAndSendAlertsToAllByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-alerts-to-hod-dyhod",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendAlertsToHodDyHodByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/home");	    
	     try {
	    	boolean flag = service.sendAlertsToHodDyHodByManual();
			logger.error("sendAlertsToHodDyHodByManual >> sendMailAlerts >> Sending mails : "+ flag); 
			
		 } catch (Exception e) {
			logger.error("sendAlertsToHodDyHodByManual() : "+e);
		 }
	     return model;
	}
	

	
	@RequestMapping(value="/generate-and-send-alerts-rajiv-ravi",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAndSendAlertsToRajivRaviByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/home");	    
	     try {
	    	logger.error("generateAndSendAlertsToRajivRaviByManual : start");
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAtertsByCronJob();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAndSendAlertsToRajivRaviByManual : "+flag);
	    	 
			flag = service.generateAndSendAlertsToRajivRaviByManual();
			logger.error("generateAndSendAlertsToRajivRaviByManual >> sendMailAlerts >> Sending mails : "+ flag); 
			//System.out.println("Sending mails : "+ flag); 
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByManual() : "+e.getMessage());
		 }
	     return model;
	}
}
