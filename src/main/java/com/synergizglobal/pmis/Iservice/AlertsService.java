package com.synergizglobal.pmis.Iservice;

public interface AlertsService {

	boolean generateAtertsByCronJob() throws Exception;

	boolean sendMailAlerts() throws Exception;

	boolean sendNotificationAlertMails() throws Exception;
	
}
