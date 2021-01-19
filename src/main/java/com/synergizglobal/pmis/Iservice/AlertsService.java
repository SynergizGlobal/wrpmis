package com.synergizglobal.pmis.Iservice;

public interface AlertsService {

	boolean generateAterts() throws Exception;
	
	boolean sendNotificationAlertMails() throws Exception;

	boolean sendAlertsToHodDyHodByManual() throws Exception;
	
	boolean sendAlertsToRajivRavi() throws Exception;

	
}
