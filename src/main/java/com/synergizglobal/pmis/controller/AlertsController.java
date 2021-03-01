package com.synergizglobal.pmis.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.User;

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
	//@Scheduled(cron = "0 50 10 * * *")	//  = every day 2:10 am.
	//@Scheduled(cron = "0 10 16 * * *")	//  = every day 4:10 pm.
	//@Scheduled(cron = "${cron.expression.generate.alerts}")
	public void generateAlertsByCronJob(){		
	     String message = "Method executed every day. Current time is :: "+ new Date();
	     
	     logger.error("generateAlertsByCronJob : "+message);	    
	     try {
	    	 
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAlertsByCronJob : "+flag);
			
	    	flag = service.sendAlertsToRajivRavi();
			logger.error("generateAlertsByCronJob >> Sending mails : "+ flag); 
			
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
	    	boolean flag = service.sendNotificationAlertMails();
		    logger.error("sendNotificationAlertMailsToAllByCronJob >> Sending mails : "+ flag);
		    //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendNotificationAlertMailsToAllByCronJob() : "+e.getMessage());
		 }
	}
	
	@RequestMapping(value="/generate-and-send-alerts-to-all",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAndSendAlertsToAllByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/home");	    
	     try {
	    	logger.error("generateAndSendAlertsToAllByManual : start");
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAterts();
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
            boolean flag = service.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAndSendAlertsToRajivRaviByManual : "+flag);
	    	 
			flag = service.sendAlertsToRajivRavi();
			logger.error("generateAndSendAlertsToRajivRaviByManual >> Sending mails : "+ flag); 
			//System.out.println("Sending mails : "+ flag); 
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-alerts-rajiv-ravi",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendAlertsToRajivRaviByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/home");	    
	     try {
	    	logger.error("sendAlertsToRajivRaviByManual : start");
	    	 
			boolean flag = service.sendAlertsToRajivRavi();
			logger.error("sendAlertsToRajivRaviByManual >> Sending mails : "+ flag); 
			//System.out.println("Sending mails : "+ flag); 
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendAlertsToRajivRaviByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/get-alerts",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getAlerts(HttpSession session){		
		 ModelAndView model = new ModelAndView(PageConstants2.alertsGrid);	    
	     try {
	    	 String user_Id = (String) session.getAttribute("USER_ID");
	    	 String userName = (String) session.getAttribute("USER_NAME");
	    	 User uObj = (User) session.getAttribute("user");
	    	 List<Alerts> alerts = service.getAlerts(uObj);
	    	 model.addObject("alerts", alerts);
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("getAlerts() : "+e.getMessage());
		 }
	     return model;
	}
	
}
