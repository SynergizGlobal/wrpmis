package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Alerts;

public interface AlertsDao {

	boolean generateAtertsByCronJob() throws Exception;

	List<Alerts> getAlertsList() throws Exception;
	
}
