package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.AlertLevelDao;
import com.synergizglobal.pmis.reference.Iservice.AlertLevelService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class AlertLevelServiceImpl implements AlertLevelService{

	@Autowired
	AlertLevelDao dao;

	@Override
	public List<TrainingType> getAlertLevelList() throws Exception {
		return dao.getAlertLevelList();
	}

	@Override
	public TrainingType getAlertLevelDetails(TrainingType obj) throws Exception {
		return dao.getAlertLevelDetails(obj);
	}

	@Override
	public boolean addAlertLevel(TrainingType obj) throws Exception {
		return dao.addAlertLevel(obj);
	}

	@Override
	public boolean updateAlertLevel(TrainingType obj) throws Exception {
		return dao.updateAlertLevel(obj);
	}

	@Override
	public boolean deleteAlertLevel(TrainingType obj) throws Exception {
		return dao.deleteAlertLevel(obj);
	}

}
