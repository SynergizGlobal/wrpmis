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
	public boolean sendNotificationAlertMails() throws Exception {
		return dao.sendNotificationAlertMails();
	}

	@Override
	public boolean sendAlertsToHodDyHodByManual() throws Exception {
		return dao.sendAlertsToHodDyHodByManual();
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

}
