package com.synergizglobal.pmis.Idao;

public interface AlertsDao {

	boolean generateAtertsByCronJob() throws Exception;

	boolean sendNotificationAlertMails() throws Exception;

	boolean sendAlertsToHodDyHodByManual() throws Exception;
	
	boolean generateAndSendAlertsToRajivRaviByManual() throws Exception;
	
}
