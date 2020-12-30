package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Alerts;

public interface AlertsService {

	boolean generateAtertsByCronJob() throws Exception;

	List<Alerts> getAlertsList() throws Exception;
	
}
