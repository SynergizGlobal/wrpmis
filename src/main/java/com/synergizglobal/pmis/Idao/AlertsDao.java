package com.synergizglobal.pmis.Idao;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.Alerts;

public interface AlertsDao {

	boolean generateAterts() throws Exception;

	boolean sendEMailNotificationWithContractAlerts(String alert_type) throws Exception;
	
	boolean sendEMailNotificationWithIssueAlerts(String alert_type) throws Exception;

	boolean sendEMailNotificationWithRiskAlerts() throws Exception;
	
	boolean sendEMailNotificationAlertsToITAdmins() throws Exception;

	List<Alerts> getAlerts(Alerts obj) throws Exception;
	
	List<Alerts> getContractorsFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getContractsFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getHODFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getWorksFilterListInAlerts(Alerts obj) throws Exception;

	List<Alerts> getAlertTypesFilterListInAlerts(Alerts obj) throws Exception;

	boolean addAlertRemarks(Alerts obj) throws Exception;

	Map<String,List<Alerts>> getAlertsForHeaderNotifications(Alerts aObj) throws Exception;

	int getAlertsCount(Alerts obj) throws Exception;

	boolean callingStoredProcedures() throws Exception;

	int getTotalRecords(Alerts obj, String searchParameter) throws Exception;

	List<Alerts> getAlertsList(Alerts obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<Alerts> getAlertTypes(Alerts aObj) throws Exception;
	
	List<Alerts> getAlertTypesForGenerateSendAlerts() throws Exception;

	List<Alerts> getAlertLevelsForGenerateSendAlerts() throws Exception;

	List<Alerts> getSendToListForGenerateSendAlerts() throws Exception;

	boolean sendAlertsToParticulars(Alerts obj) throws Exception;

	Map<String,List<Alerts>> getContractAlerts(Alerts obj) throws Exception;
	
	List<Alerts> getHodListInAlertsReport(Alerts obj) throws Exception;

	List<Alerts> getWorksListInAlertsReport(Alerts obj) throws Exception;

	List<Alerts> getAlertLevelsListInAlertsReport(Alerts obj) throws Exception;

	List<Alerts> getAlertTypesListInAlertsReport(Alerts obj) throws Exception;
	
}
