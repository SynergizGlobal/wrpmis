package com.synergizglobal.pmis.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.AlertsDao;
import com.synergizglobal.pmis.Iservice.AlertsService;

@Service
public class AlertsServiceImpl implements AlertsService{
	@Autowired
	AlertsDao dao;
	
	@Override
	public boolean generateAtertsByCronJob() throws Exception {
		return dao.generateAtertsByCronJob();
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
	public boolean generateAndSendAlertsToRajivRaviByManual() throws Exception {
		return dao.generateAndSendAlertsToRajivRaviByManual();
	}

}
