package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RiskWorkHodDao;
import com.synergizglobal.pmis.reference.Iservice.RiskWorkHodService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RiskWorkHodServiceImpl implements RiskWorkHodService{

	@Autowired
	RiskWorkHodDao dao;

	@Override
	public List<TrainingType> getRiskWorkHODDetails(TrainingType obj) throws Exception {
		return dao.getRiskWorkHODDetails(obj);
	}

	@Override
	public List<TrainingType> getWorkDetails(TrainingType obj) throws Exception {
		return dao.getWorkDetails(obj);
	}

	@Override
	public List<TrainingType> getHODDetails(TrainingType obj) throws Exception {
		return dao.getHODDetails(obj);
	}

	@Override
	public boolean addRiskWorkHOD(TrainingType obj) throws Exception {
		return dao.addRiskWorkHOD(obj);
	}

	@Override
	public boolean updateRiskWorkHOD(TrainingType obj) throws Exception {
		return dao.updateRiskWorkHOD(obj);
	}

	@Override
	public boolean deleteRiskWorkHOD(TrainingType obj) throws Exception {
		return dao.deleteRiskWorkHOD(obj);
	}
}
