package com.synergizglobal.pmis.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value="/generate-alerts-manually",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAlertsManually(){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
	     try {
	    	logger.error("generateAlertsManually : start");
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAlertsManually : "+flag);
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/get-alerts",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getAlertsList(HttpSession session){		
		 ModelAndView model = new ModelAndView(PageConstants2.alertsGrid);	    
	     try {
	    	 //String user_Id = (String) session.getAttribute("USER_ID");
	    	 //String userName = (String) session.getAttribute("USER_NAME");
	    	 User uObj = (User) session.getAttribute("user");
	    	 model.addObject("email_id", uObj.getEmail_id());
	    	 model.addObject("user_role_name", uObj.getUser_role_name_fk());
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("getAlerts() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value = "/ajax/getAlerts", method = {RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractorsFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getContractorsFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getContractorsFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorsFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getContractsFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getContractsFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getHODFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getHODFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getHODFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getWorksFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getWorksFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getAlertTypesFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getAlertTypesFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getAlertTypesFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlertTypesFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-alert-remarks",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addAlertRemarks(@ModelAttribute Alerts obj,HttpSession session,RedirectAttributes attributes){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
	     try {
	    	 String user_Id = (String) session.getAttribute("USER_ID");
	    	 //String userName = (String) session.getAttribute("USER_NAME");
	    	 boolean flag = service.addAlertRemarks(obj);
	    	 if(flag) {
	    		 attributes.addFlashAttribute("success", "Remarks addedd successfully.");
	    	 }else {
	    		 attributes.addFlashAttribute("error", "Adding remarks failed.");
	    	 }
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("addAlertRemarks() : "+e.getMessage());
		 }
	     return model;
	}
	
}
