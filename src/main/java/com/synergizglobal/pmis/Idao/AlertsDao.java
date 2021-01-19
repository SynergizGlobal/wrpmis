package com.synergizglobal.pmis.Idao;

public interface AlertsDao {

	boolean generateAterts() throws Exception;

	boolean sendNotificationAlertMails() throws Exception;

	boolean sendAlertsToHodDyHodByManual() throws Exception;
	
	boolean sendAlertsToRajivRavi() throws Exception;
	
}
