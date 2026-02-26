package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.AlertConditionsDao;
import com.synergizglobal.wrpmis.Iservice.AlertConditionsService;
import com.synergizglobal.wrpmis.model.AlertConditions;

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
