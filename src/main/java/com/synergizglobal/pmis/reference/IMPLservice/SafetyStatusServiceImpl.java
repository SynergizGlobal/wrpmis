package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SafetyStatusDao;
import com.synergizglobal.pmis.reference.Iservice.SafetyStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class SafetyStatusServiceImpl implements SafetyStatusService {

	@Autowired
	SafetyStatusDao dao;

	@Override
	public List<Safety> getSafetyStatusList() throws Exception {
		return dao.getSafetyStatusList();
	}

	@Override
	public boolean addSafetyStatus(Safety obj) throws Exception {
		return dao.addSafetyStatus(obj);
	}

	@Override
	public TrainingType getSafetyStatusDetails(TrainingType obj) throws Exception {
		return dao.getSafetyStatusDetails(obj);
	}

	@Override
	public boolean updateSafetyStatus(TrainingType obj) throws Exception {
		return dao.updateSafetyStatus(obj);
	}

	@Override
	public boolean deleteSafetyStatus(TrainingType obj) throws Exception {
		return dao.deleteSafetyStatus(obj);
	}
}
