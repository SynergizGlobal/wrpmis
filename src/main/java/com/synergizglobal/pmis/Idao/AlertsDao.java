package com.synergizglobal.pmis.Idao;

public interface AlertsDao {

	boolean generateAtertsByCronJob() throws Exception;

	boolean sendMailAlerts() throws Exception;

	boolean sendNotificationAlertMails() throws Exception;
	
}
