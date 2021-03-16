package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RiskAreaDao;
import com.synergizglobal.pmis.reference.Iservice.RiskAreaService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RiskAreaServiceImpl implements RiskAreaService{
	@Autowired
	RiskAreaDao dao;

	@Override
	public List<Risk> getRiskAreasList() throws Exception {
		return dao.getRiskAreasList();
	}

	@Override
	public boolean addRiskArea(Risk obj) throws Exception {
		return dao.addRiskArea(obj);
	}

	@Override
	public TrainingType getRiskAreaDetails(TrainingType obj) throws Exception {
		return dao.getRiskAreaDetails(obj);
	}

	@Override
	public boolean updateRiskArea(TrainingType obj) throws Exception {
		return dao.updateRiskArea(obj);
	}

	@Override
	public boolean deleteRiskArea(TrainingType obj) throws Exception {
		return dao.deleteRiskArea(obj);
	}
}
