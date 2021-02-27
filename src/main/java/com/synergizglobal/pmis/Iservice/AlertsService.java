package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.User;

public interface AlertsService {

	boolean generateAterts() throws Exception;
	
	boolean sendNotificationAlertMails() throws Exception;

	boolean sendAlertsToHodDyHodByManual() throws Exception;
	
	boolean sendAlertsToRajivRavi() throws Exception;

	List<Alerts> getAlerts(User uObj) throws Exception;
	
}
