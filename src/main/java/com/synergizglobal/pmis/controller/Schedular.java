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
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Issue;

@Controller
public class Schedular {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(Schedular.class);
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	AlertsService alertService;
	
	@Autowired
	IssuesReportController issueReportController;
	
	@Value("${common.error.message}")
	public String commonError;
	
	
	/**********************************************************************************/
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	@Scheduled(cron = "${cron.expression.user.login.timeout}")
	public void userLoginTimeout(){
	     try {
	    	 //logger.error("userLoginTimeout : Method executed at > "+new Date());
             boolean flag = homeService.userLoginTimeout();
	    	 //logger.error("userLoginTimeout : "+flag);
		 } catch (Exception e) {
			 e.printStackTrace();
			 logger.error("userLoginTimeout() : "+e.getMessage());
		 }
	}
	/**********************************************************************************/
	
	
	
	//@Scheduled(cron = "0 0/3 * * * *")	//  = every minute.
	//@Scheduled(cron = "0 50 10 * * *")	//  = every day 2:10 am.
	//@Scheduled(cron = "0 10 16 * * *")	//  = every day 4:10 pm.
	//@Scheduled(cron = "${cron.expression.generate.alerts}")
	public void generateAlertsByCronJob(){		
	     String message = "Method executed every day. Current time is :: "+ new Date();
	     
	     logger.error("generateAlertsByCronJob : "+message);	    
	     try {
	    	 
	    	//System.out.println("Start "+ new Date());
            boolean flag = alertService.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAlertsByCronJob : "+flag);
	    	
	    	//flag = alertService.sendRiskNotificationAlertMails();
			//logger.error("sendRiskNotificationAlertMails >> Sending mails : "+ flag); 
			
			flag = alertService.sendAlertsToRajivRavi();
			logger.error("generateAlertsByCronJob >> Sending mails : "+ flag); 
			
			//Calling stored procedures
			flag = alertService.callingStoredProcedures();
			logger.error("generateAlertsByCronJob >> Run Procedures : "+ flag);
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByCronJob() : "+e.getMessage());
		 }
	}
	
	//@Scheduled(cron = "0 25 15 * * MON")
	//@Scheduled(cron = "${cron.expression.sending.alert.mails}")
	public void sendNotificationAlertMailsToAllByCronJob(){		
	     String message = "Method executed every Monday. Current time is :: "+ new Date();
	     
	     logger.error("sendNotificationAlertMailsToAllByCronJob : "+message);	    
	     try {
	    	boolean flag = alertService.sendNotificationAlertMails();
		    logger.error("sendNotificationAlertMailsToAllByCronJob >> Sending mails : "+ flag);
		    //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendNotificationAlertMailsToAllByCronJob() : "+e.getMessage());
		 }
	}
	
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	//@Scheduled(cron = "${cron.expression.send.mail.with.open.issues}")
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
		
	/**********************************************************************************/
	
	@RequestMapping(value="/send-mail-open-issues",method=RequestMethod.GET)
	public ModelAndView sendMailWithOpenIssuesByManual(){
		ModelAndView model = new ModelAndView("redirect:/home");
	     try {
	    	 logger.error("sendMailWithOpenIssuesByManual : Method executed at > "+new Date());
	    	 Issue obj = new Issue();
             boolean flag = issueReportController.sendMailWithOpenIssues(obj);
	    	 logger.error("sendMailWithOpenIssuesByManual : "+flag);
		 } catch (Exception e) {
			 e.printStackTrace();
			 logger.error("sendMailWithOpenIssuesByManual() : "+e.getMessage());
		 }
	     return model;
	}
}
