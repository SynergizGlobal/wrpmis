package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RiskPriorityDao;
import com.synergizglobal.pmis.reference.Iservice.RiskPriorityService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class RiskPriorityServiceImpl implements RiskPriorityService{

	@Autowired
	RiskPriorityDao dao;

	@Override
	public List<Safety> getRiskPriorityList() throws Exception {
		return dao.getRiskPriorityList();
	}

	@Override
	public boolean addRiskPriority(Safety obj) throws Exception {
		return dao.addRiskPriority(obj);
	}

	@Override
	public TrainingType getRiskPriorityDetails(TrainingType obj) throws Exception {
		return dao.getRiskPriorityDetails(obj);
	}

	@Override
	public boolean updateRiskPriority(TrainingType obj) throws Exception {
		return dao.updateRiskPriority(obj);
	}

	@Override
	public boolean deleteRiskPriority(TrainingType obj) throws Exception {
		return dao.deleteRiskPriority(obj);
	}
}
