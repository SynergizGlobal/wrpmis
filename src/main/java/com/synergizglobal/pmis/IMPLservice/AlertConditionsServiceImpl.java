package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.AlertConditionsDao;
import com.synergizglobal.pmis.Iservice.AlertConditionsService;
import com.synergizglobal.pmis.model.AlertConditions;

@Service
public class AlertConditionsServiceImpl implements AlertConditionsService{
	@Autowired
	AlertConditionsDao dao;
	
	@Override
	public List<AlertConditions> getAlertTypes() throws Exception {
		return dao.getAlertTypes();
	}

	@Override
	public List<AlertConditions> getAlertConditions(AlertConditions obj) throws Exception {
		return dao.getAlertConditions(obj);
	}

	@Override
	public List<AlertConditions> getAlertCondition(AlertConditions obj) throws Exception {
		return dao.getAlertCondition(obj);
	}

	@Override
	public boolean updateAlertCondition(AlertConditions obj) throws Exception {
		return dao.updateAlertCondition(obj);
	}

}
