package com.synergizglobal.pmis.Iservice;

public interface AlertsService {

	boolean generateAtertsByCronJob() throws Exception;
	
	boolean sendNotificationAlertMails() throws Exception;

	boolean sendAlertsToHodDyHodByManual() throws Exception;
	
	boolean generateAndSendAlertsToRajivRaviByManual() throws Exception;

	
}
