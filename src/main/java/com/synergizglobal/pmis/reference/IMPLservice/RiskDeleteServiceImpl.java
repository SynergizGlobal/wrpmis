package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RiskDeleteDao;
import com.synergizglobal.pmis.reference.Iservice.RiskDeleteService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RiskDeleteServiceImpl implements RiskDeleteService{

	@Autowired
	RiskDeleteDao dao;

	@Override
	public List<TrainingType> getRisksList(TrainingType obj) throws Exception {
		return dao.getRisksList(obj);
	}

	@Override
	public boolean deleteRisk(TrainingType obj) throws Exception {
		return dao.deleteRisk(obj);
	}

	@Override
	public List<TrainingType> getSubWorkFilterList(TrainingType obj) throws Exception {
		return dao.getSubWorkFilterList(obj);
	}

}
