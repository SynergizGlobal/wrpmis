package com.synergizglobal.pmis.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.User;

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
	
	@Autowired
	ContractReportController contractReportController;
	
	@Autowired
	UserLoginReportController userLoginReportController;
	
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
	     logger.error("generateAlertsByCronJob : Method executed every day. Current time is :"+ new Date());	    
	     try {
	    	 
	    	//System.out.println("Start "+ new Date());
            boolean flag = alertService.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAlertsByCronJob : "+flag);
	    	
	    	flag = alertService.sendEMailNotificationWithRiskAlerts();
			logger.error("sendEMailNotificationWithRiskAlerts >> Sent mails : "+ flag); 
			
			flag = alertService.sendEMailNotificationAlertsToITAdmins();
			logger.error("sendEMailNotificationAlertsToITAdmins >> Sent mails : "+ flag); 
			
			//Calling stored procedures
			flag = alertService.callingStoredProcedures();
			logger.error("callingStoredProcedures >> Run Procedures : "+ flag);
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByCronJob() : "+e.getMessage());
		 }
	}
	
	//@Scheduled(cron = "0 0/2 * * * *")
	//@Scheduled(cron = "${cron.expression.sending.alert.mails.by.alert.type}")
	public void sendNotificationAlertMailsToAllByCronJob(){		   
	     logger.error("sendNotificationAlertMailsToAllByCronJob : Current time is :"+ new Date());	    
	     try {
	    	   Date date = new Date();
			   Calendar cal = Calendar.getInstance();
	           cal.setTime(date); // don't forget this if date is arbitrary
	             
	           SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
	           SimpleDateFormat dayOfWeekTextFormat = new SimpleDateFormat("EEEE");
	           String dayOfWeekText = dayOfWeekTextFormat.format(date).toUpperCase();
	           //int month = cal.get(Calendar.MONTH); // 0 being January
	           
				String alert_type = null;
				if(dayOfWeekText.equals("MONDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_BANK_GUARANTEE;
				}else if(dayOfWeekText.equals("TUESDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_INSURANCE;
				}else if(dayOfWeekText.equals("WEDNESDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_CONTRACT_PERIOD;
				}else if(dayOfWeekText.equals("THURSDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_CONTRACT_VALUE;
				}else if(dayOfWeekText.equals("FRIDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_ISSUE;
				}
				
				if(!StringUtils.isEmpty(alert_type)) {
					if(dayOfWeekText.equals("FRIDAY")) {
					    boolean flag = alertService.sendEMailNotificationWithIssueAlerts(alert_type);
					    logger.error("sendEMailNotificationWithIssueAlertsByCronJob >> Sent mails : "+ flag);
					}else {
						boolean flag = alertService.sendEMailNotificationWithContractAlerts(alert_type);
						logger.error("sendEMailNotificationWithContractAlertsByCronJob >> "+alert_type+" Sent mails : "+ flag);
					}
				}
	           
				/*String alert_type = null;
				if(dayOfWeekText.equals("WEDNESDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_CONTRACT;
				   boolean flag = alertService.sendEMailNotificationWithContractAlerts(alert_type);
				   logger.error("sendEMailNotificationWithContractAlertsByCronJob >> Sent mails : "+ flag);
				}else if(dayOfWeekText.equals("FRIDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_ISSUE;
				   boolean flag = alertService.sendEMailNotificationWithIssueAlerts(alert_type);
				   logger.error("sendEMailNotificationWithIssueAlertsByCronJob >> Sent mails : "+ flag);
				}*/
			   
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendNotificationAlertMailsToAllByCronJob() : "+e.getMessage());
		 }
	}
	
	/******************************************************************************************/
	
	
	
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
	
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	//@Scheduled(cron = "0 10 12 * * MON")
	//@Scheduled(cron = "${cron.expression.sending.user.login.report.mails}")
	public void sendUserLoginReportByCronJob(){		
	     logger.error("sendUserLoginReportByCronJob : Current time is :"+ new Date());	    
	     try {
	    	 User uObj = new User();
	    	 boolean isDownload = false;
	    	 boolean flag = userLoginReportController.sendLast7DaysUserLoginDeatils(uObj,null,isDownload);
		     logger.error("sendUserLoginReportByCronJob >> Sending mails : "+ flag);
		     //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendUserLoginReportByCronJob() : "+e.getMessage());
		 }
	}
	
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	//@Scheduled(cron = "${cron.expression.send.mail.with.contract.bg.insurance.report}")
	public void sendMailWithContractBGInsuranceReport(){
		try {
	    	 logger.error("sendMailWithContractBGInsuranceReport : Method executed at > "+new Date());
	    	 contractReportController.contractReportAutoEmail();
	    	 logger.error("sendMailWithContractBGInsuranceReport : end");
		} catch (Exception e) {
			 e.printStackTrace();
			 logger.error("sendMailWithContractBGInsuranceReport() : "+e.getMessage());
		}
	}
		
	/**********************************************************************************/
	
	
	
}
