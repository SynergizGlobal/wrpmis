package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.AlertsDao;
import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.model.Alerts;

@Service
public class AlertsServiceImpl implements AlertsService{
	@Autowired
	AlertsDao dao;
	
	@Override
	public boolean generateAterts() throws Exception {
		return dao.generateAterts();
	}

	@Override
	public boolean sendNotificationAlertMails(String alert_type) throws Exception {
		return dao.sendNotificationAlertMails(alert_type);
	}

	@Override
	public boolean sendAlertsToRajivRavi() throws Exception {
		return dao.sendAlertsToRajivRavi();
	}

	@Override
	public List<Alerts> getAlerts(Alerts obj) throws Exception {
		return dao.getAlerts(obj);
	}

	@Override
	public List<Alerts> getContractorsFilterListInAlerts(Alerts obj) throws Exception {
		return dao.getContractorsFilterListInAlerts(obj);
	}

	@Override
	public List<Alerts> getContractsFilterListInAlerts(Alerts obj) throws Exception {
		return dao.getContractsFilterListInAlerts(obj);
	}

	@Override
	public List<Alerts> getHODFilterListInAlerts(Alerts obj) throws Exception {
		return dao.getHODFilterListInAlerts(obj);
	}

	@Override
	public List<Alerts> getWorksFilterListInAlerts(Alerts obj) throws Exception {
		return dao.getWorksFilterListInAlerts(obj);
	}

	@Override
	public List<Alerts> getAlertTypesFilterListInAlerts(Alerts obj) throws Exception {
		return dao.getAlertTypesFilterListInAlerts(obj);
	}

	@Override
	public boolean addAlertRemarks(Alerts obj) throws Exception {
		return dao.addAlertRemarks(obj);
	}

	@Override
	public Map<String,List<Alerts>> getAlertsForHeaderNotifications(Alerts aObj) throws Exception {
		return dao.getAlertsForHeaderNotifications(aObj);
	}

	@Override
	public int getAlertsCount(Alerts obj) throws Exception {
		return dao.getAlertsCount(obj);
	}

	@Override
	public boolean callingStoredProcedures() throws Exception {
		return dao.callingStoredProcedures();
	}

	@Override
	public int getTotalRecords(Alerts obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<Alerts> getAlertsList(Alerts obj, int startIndex, int offset, String searchParameter) throws Exception {
		return dao.getAlertsList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<Alerts> getAlertTypes(Alerts aObj) throws Exception {
		return dao.getAlertTypes(aObj);
	}

	@Override
	public boolean sendRiskNotificationAlertMails() throws Exception {
		return dao.sendRiskNotificationAlertMails();
	}

	@Override
	public List<Alerts> getAlertTypesForGenerateSendAlerts() throws Exception {
		return dao.getAlertTypesForGenerateSendAlerts();
	}

	@Override
	public List<Alerts> getAlertLevelsForGenerateSendAlerts() throws Exception {
		return dao.getAlertLevelsForGenerateSendAlerts();
	}

	@Override
	public List<Alerts> getSendToListForGenerateSendAlerts() throws Exception {
		return dao.getSendToListForGenerateSendAlerts();
	}

	@Override
	public boolean sendAlertsToParticulars(Alerts obj) throws Exception {
		return dao.sendAlertsToParticulars(obj);
	}

}
