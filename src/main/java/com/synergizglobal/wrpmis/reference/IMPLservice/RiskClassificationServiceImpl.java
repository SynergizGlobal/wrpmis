package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DrawingTypeDao;
import com.synergizglobal.wrpmis.reference.Idao.RiskClassificationDao;
import com.synergizglobal.wrpmis.reference.Iservice.RiskClassificationService;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class RiskClassificationServiceImpl implements RiskClassificationService{

	@Autowired
	RiskClassificationDao dao;

	@Override
	public List<Risk> getRiskClassificationsList() throws Exception {
		return dao.getRiskClassificationsList();
	}

	@Override
	public boolean addRiskClassification(Risk obj) throws Exception {
		return dao.addRiskClassification(obj);
	}

	@Override
	public TrainingType getRiskClassificationDetails(TrainingType obj) throws Exception {
		return dao.getRiskClassificationDetails(obj);
	}

	@Override
	public boolean updateRiskClassification(TrainingType obj) throws Exception {
		return dao.updateRiskClassification(obj);
	}

	@Override
	public boolean deleteRiskClassification(TrainingType obj) throws Exception {
		return dao.deleteRiskClassification(obj);
	}
}
