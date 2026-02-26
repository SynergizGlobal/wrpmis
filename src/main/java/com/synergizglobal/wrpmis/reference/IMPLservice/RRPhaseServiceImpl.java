package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.RRPhaseDao;
import com.synergizglobal.wrpmis.reference.Iservice.RRPhaseService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class RRPhaseServiceImpl implements RRPhaseService{

	@Autowired
	RRPhaseDao dao;
	
	@Override
	public TrainingType getRRPhaseDetails(TrainingType obj) throws Exception {
		return dao.getRRPhaseDetails(obj);
	}

	@Override
	public boolean addRRPhase(TrainingType obj) throws Exception {
		return dao.addRRPhase(obj);
	}

	@Override
	public boolean updateRRPhase(TrainingType obj) throws Exception {
		return dao.updateRRPhase(obj);
	}

	@Override
	public boolean deleteRRPhase(TrainingType obj) throws Exception {
		return dao.deleteRRPhase(obj);
	}

}
