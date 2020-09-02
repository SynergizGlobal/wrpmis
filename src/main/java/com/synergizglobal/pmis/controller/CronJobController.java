package com.synergizglobal.pmis.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Mail;
import com.synergizglobal.pmis.model.Notification;

@Component
public class CronJobController {
	private static Logger logger = Logger.getLogger(CronJobController.class);
	
	
	/**
	 * This method getDueActivities will execute as per cron job time it will check the due activities, this method return type is void
	 */
	
	//@Scheduled(cron = "0 0/1 * * * *")	//  = every minute.
	@Scheduled(cron = "0 10 9 ? * *")	//  = every day.
	public void getDueActivities(){		
	    logger.error("getDueActivities() start: ");	    
	    //System.out.println("getDueActivities() start: ");
	     try {
	    	EMailSender emailSender = new EMailSender();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String userDate = "";	
			String currentDate = "";
			if(!StringUtils.isEmpty(userDate)){
				date = sdf.parse(userDate);
				currentDate = sqlDate.format(date);
			}else{
				currentDate = sqlDate.format(date);
			}
	    	//System.out.println("currentDate "+currentDate);
			 Calendar cal = Calendar.getInstance();
             cal.setTime(date); // don't forget this if date is arbitrary
             
             SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
             String month = monthFormat.format(date).toUpperCase();
             //int month = cal.get(Calendar.MONTH); // 0 being January
             int year = cal.get(Calendar.YEAR);
             
             int day = cal.get(Calendar.DAY_OF_MONTH);                
             int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
             //System.out.println("Days : "+days);
             //System.out.println("day : "+day);
             
             
             /*cal.add(Calendar.MONTH, 1);  
             cal.set(Calendar.DAY_OF_MONTH, 1);  
             cal.add(Calendar.DATE, -1);  

             Date lastDayOfMonth = cal.getTime();             
             System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth)); */
             
          //   boolean flag = aService.createNotifications();
             
             List<Notification> dueActivities = null;
            		 
             //dueActivities = aService.getDueActivities(null);
             String emailSubject = "Delayed Activities(On: "+day +" "+month+", "+year+")";
             
             Mail mail = new Mail();
		     mail.setMailFrom("syntrack@synergizglobal.com");
		     mail.setMailTo(CommonConstants.NOTIFICATIONS_EMAIL);
		     mail.setMailSubject(emailSubject);
		     mail.setTemplateName("DueActivities.vm");
		     mail.setDueActivities(dueActivities);
		     
		     if(dueActivities != null && dueActivities.size() > 0){
            	 logger.error("getDueActivities >> sendEmailWithDueActivities() : Start ");	
            	 //emailSender.sendEmailWithDueActivities(mail); 
            	 logger.error("getDueActivities >> sendEmailWithDueActivities() : End ");	
             }
		     
		     logger.error("getDueActivities() end: ");	  
		     //System.out.println("getDueActivities() end: ");
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("getDueActivities() : "+e.getMessage());
		 }
	}
}
