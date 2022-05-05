package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RiskSubAreaDao;
import com.synergizglobal.pmis.reference.Iservice.RiskSubAreaService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RiskSubAreaServiceImpl implements RiskSubAreaService{

	@Autowired
	RiskSubAreaDao dao;

	@Override
	public List<Risk> getRiskSubAreasList() throws Exception {
		return dao.getRiskSubAreasList();
	}

	@Override
	public boolean addRiskSubArea(Risk obj) throws Exception {
		return dao.addRiskSubArea(obj);
	}

	@Override
	public TrainingType getRiskSubAreaDetails(TrainingType obj) throws Exception {
		return dao.getRiskSubAreaDetails(obj);
	}

	@Override
	public List<TrainingType> getRiskAreaList(TrainingType obj) throws Exception {
		return dao.getRiskAreaList(obj);
	}

	@Override
	public boolean updateRiskSubArea(TrainingType obj) throws Exception {
		return dao.updateRiskSubArea(obj);
	}

	@Override
	public boolean deleteRiskSubArea(TrainingType obj) throws Exception {
		return dao.deleteRiskSubArea(obj);
	}

	@Override
	public List<TrainingType> getSubAreaDetails(TrainingType obj) throws Exception {
		return dao.getSubAreaDetails(obj);
	}
}
