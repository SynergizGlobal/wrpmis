package com.synergizglobal.pmis.Idao;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.Alerts;

public interface AlertsDao {

	boolean generateAterts() throws Exception;

	boolean sendNotificationAlertMails() throws Exception;

	boolean sendAlertsToHodDyHodByManual() throws Exception;
	
	boolean sendAlertsToRajivRavi() throws Exception;

	List<Alerts> getAlerts(Alerts obj) throws Exception;
	
	List<Alerts> getContractorsFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getContractsFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getHODFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getWorksFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getAlertTypesFilterListInAlerts(Alerts obj) throws Exception;

	boolean addAlertRemarks(Alerts obj) throws Exception;

	Map<String,List<Alerts>> getAlertsForHeaderNotifications(Alerts aObj) throws Exception;
	
}
