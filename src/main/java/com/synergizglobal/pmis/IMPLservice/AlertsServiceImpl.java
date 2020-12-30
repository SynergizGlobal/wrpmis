package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

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
	public boolean generateAtertsByCronJob() throws Exception {
		return dao.generateAtertsByCronJob();
	}

	@Override
	public List<Alerts> getAlertsList() throws Exception {
		return dao.getAlertsList();
	}

}
