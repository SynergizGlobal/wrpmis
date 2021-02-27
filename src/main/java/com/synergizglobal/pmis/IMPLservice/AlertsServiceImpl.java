package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.AlertsDao;
import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.User;

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
	public List<Alerts> getAlerts(User uObj) throws Exception {
		return dao.getAlerts(uObj);
	}

}
