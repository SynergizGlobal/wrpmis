package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.AlertLevelDao;
import com.synergizglobal.wrpmis.reference.Idao.AlertTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.AlertTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class AlertTypeServiceImpl implements AlertTypeService{
	
	@Autowired
	AlertTypeDao dao;

	@Override
	public TrainingType getAlertTypeDetails(TrainingType obj) throws Exception {
		return dao.getAlertTypeDetails(obj);
	}

	@Override
	public boolean addAlertType(TrainingType obj) throws Exception {
		return dao.addAlertType(obj);
	}

	@Override
	public boolean updateAlertType(TrainingType obj) throws Exception {
		return dao.updateAlertType(obj);
	}

	@Override
	public boolean deleteAlertType(TrainingType obj) throws Exception {
		return dao.deleteAlertType(obj);
	}
	
	

}
