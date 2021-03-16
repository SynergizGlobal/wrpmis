package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SafetyImpactDao;
import com.synergizglobal.pmis.reference.Iservice.SafetyImpactService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class SafetyImpactServiceImpl implements SafetyImpactService{

	@Autowired
	SafetyImpactDao dao;

	@Override
	public List<Safety> getSafetyImpactsList() throws Exception {
		return dao.getSafetyImpactsList();
	}

	@Override
	public boolean addSafetyImpact(Safety obj) throws Exception {
		return dao.addSafetyImpact(obj);
	}

	@Override
	public TrainingType getSafetyImpactDetails(TrainingType obj) throws Exception {
		return dao.getSafetyImpactDetails(obj);
	}

	@Override
	public boolean updateSafetyImpact(TrainingType obj) throws Exception {
		return dao.updateSafetyImpact(obj);
	}

	@Override
	public boolean deleteSafetyImpact(TrainingType obj) throws Exception {
		return dao.deleteSafetyImpact(obj);
	}
}
