package com.synergizglobal.wrpmis.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.synergizglobal.wrpmis.Iservice.AlertsService;
import com.synergizglobal.wrpmis.Iservice.HomeService;
import com.synergizglobal.wrpmis.Iservice.IssueService;
import com.synergizglobal.wrpmis.common.UrlGenerator;
import com.synergizglobal.wrpmis.constants.CommonConstants2;
import com.synergizglobal.wrpmis.model.Issue;
import com.synergizglobal.wrpmis.model.User;

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
	IssueService issueService;
	
	@Autowired
	AlertsService alertService;	
	
	@Autowired
	IssuesReportController issueReportController;
	
	@Autowired
	ContractReportController contractReportController;
	
	@Autowired
	LoginController loginController;
	
	@Autowired
	UserLoginReportController userLoginReportController;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${run.cron.jobs}")
	public boolean is_cron_jobs_enabled;
	
	@Value("${run.cron.jobs.in.qa}")
	public boolean is_cron_jobs_enabled_in_qa;
	
	/**********************************************************************************/
	
	@Scheduled(cron = "${cron.expression.user.login.timeout}")
	public void userLoginTimeout(){
		if(is_cron_jobs_enabled || is_cron_jobs_enabled_in_qa) {
			try {
		    	 //logger.error("userLoginTimeout : Method executed at > "+new Date());
	             boolean flag = homeService.userLoginTimeout();
		    	 //logger.error("userLoginTimeout : "+flag);
			 } catch (Exception e) {
				 e.printStackTrace();
				 logger.error("userLoginTimeout() : "+e.getMessage());
			 }
		}
	}
	/**********************************************************************************/	
	
	/*@Scheduled(cron = "${cron.expression.generate.alerts}")
	public void generateAlertsByCronJob(){	
		if(is_cron_jobs_enabled || is_cron_jobs_enabled_in_qa) {
		     logger.error("generateAlertsByCronJob : Method executed every day. Current time is :"+ new Date());	    
		     try {
		    	 
		    	//System.out.println("Start "+ new Date());
	            boolean flag = alertService.generateAterts();
	            //System.out.println("End "+ new Date());
		    	logger.error("generateAlertsByCronJob : "+flag);
		    	
		    	if(is_cron_jobs_enabled) {
			    	flag = alertService.sendEMailNotificationWithRiskAlerts();
					logger.error("sendEMailNotificationWithRiskAlerts >> Sent mails : "+ flag); 
					
					flag = alertService.sendEMailNotificationAlertsToITAdmins();
					logger.error("sendEMailNotificationAlertsToITAdmins >> Sent mails : "+ flag); 
		    	}
				
			 } catch (Exception e) {
				 e.printStackTrace();
				logger.error("generateAlertsByCronJob() : "+e.getMessage());
			 }
		}
	}*/
	
	@Scheduled(cron = "${cron.expression.run.procedures}")
	public void callingProceduresByCronJob(){	
		if(is_cron_jobs_enabled) {    
		     try {		    	
				//Calling stored procedures
				boolean flag = alertService.callingStoredProcedures();
				logger.error("callingProceduresByCronJob >> Run Procedures : "+ flag);
				
			 } catch (Exception e) {
				 e.printStackTrace();
				logger.error("callingProceduresByCronJob() : "+e.getMessage());
			 }
		}
	}
	
	
	/*@Scheduled(cron = "${cron.expression.generate.assign.responsibility}")
	public void generateAutoResponsibilityByCronJob(){	
		if(is_cron_jobs_enabled || is_cron_jobs_enabled_in_qa) {
		     logger.error("generateAutoResponsibilityByCronJob : Method executed every day. Current time is :"+ new Date());	    
		     try {
		    	 
		    	 logger.error("generateAutoResponsibilityByCronJob> "+new Date());
		    	 loginController.autoResponsibility();
			 } catch (Exception e) {
				 e.printStackTrace();
				logger.error("generateAutoResponsibilityByCronJob() : "+e.getMessage());
			 }
		}
	}*/	
	
	/*@Scheduled(cron = "${cron.expression.sending.alert.mails.by.alert.type}")
	public void sendNotificationAlertMailsToAllByCronJob(){		
		if(is_cron_jobs_enabled) {
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
		           

				   
			 } catch (Exception e) {
				 e.printStackTrace();
				logger.error("sendNotificationAlertMailsToAllByCronJob() : "+e.getMessage());
			 }
		}
	}*/
	
	/******************************************************************************************/
	
	@Scheduled(cron = "${cron.expression.send.mail.with.open.issues}")
	public void sendMailWithOpenIssues(){
		if(is_cron_jobs_enabled) {
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
	
	@Scheduled(cron = "${cron.expression.send.reminder.emails}")
	public void sendReminderEmails() {
		if(is_cron_jobs_enabled || is_cron_jobs_enabled_in_qa) {
	        try {
	            logger.info("sendReminderEmails: Job started at > " + new Date());

	            List<Issue> unresolvedIssues = issueService.getUnresolvedIssues();

	            for (Issue issue : unresolvedIssues) {
	            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            	String issueDate = issue.getDate(); 

	                     Date reportingDate = formatter.parse(issueDate);
	                     Date currentDate = new Date();

	                     long differenceInMilliseconds = currentDate.getTime() - reportingDate.getTime();
	                     int daysPending = (int) (differenceInMilliseconds / (1000 * 60 * 60 * 24));

	                     // Send reminders only for 7, 14, 21, or 28 days
	                     if (daysPending == 7 || daysPending == 14 || daysPending == 21 || daysPending == 28) {
	                         boolean flag = issueService.sendReminderEmail(issue, daysPending);
	                         if (flag) {
	                             logger.info("Reminder email sent for Issue ID: " + issue.getIssue_id() + ", Days Pending: " + daysPending);
	                         } else {
	                             logger.warn("Failed to send reminder email for Issue ID: " + issue.getIssue_id());
	                             boolean flag1 = issueService.sendErrorReminderEmail(issue, daysPending);
	                         }
	                     }

	            }

	            logger.info("sendReminderEmails: Job completed");
	        } catch (Exception e) {
	            logger.error("sendReminderEmails: Exception occurred - {}", e);
	        }
	    }
	}
	
	

	
	private int calculateDaysPending(String date) {
	    // Define the custom date format
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	    // Parse the input date string to LocalDate using the formatter
	    LocalDate reportingDate = LocalDate.parse(date, formatter);

	    // Calculate the number of days between the reporting date and today's date
	    return Period.between(reportingDate, LocalDate.now()).getDays();
	}
	@Scheduled(cron = "${cron.expression.sending.user.login.report.mails}")
	public void sendUserLoginReportByCronJob(){		
		if(is_cron_jobs_enabled) {
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
	}
	
	@Scheduled(cron = "${cron.expression.send.mail.with.contract.bg.insurance.report}")
	public void sendMailWithContractBGInsuranceReport(){
		if(is_cron_jobs_enabled) {
			try {
		    	 logger.error("sendMailWithContractBGInsuranceReport : Method executed at > "+new Date());
		    	 contractReportController.contractReportAutoEmail();
		    	 logger.error("sendMailWithContractBGInsuranceReport : end");
			} catch (Exception e) {
				 e.printStackTrace();
				 logger.error("sendMailWithContractBGInsuranceReport() : "+e.getMessage());
			}
		}
	}
		
	/**********************************************************************************/
	
	
	
}
