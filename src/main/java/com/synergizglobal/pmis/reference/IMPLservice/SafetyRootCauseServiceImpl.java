package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SafetyRootCauseDao;
import com.synergizglobal.pmis.reference.Iservice.SafetyRootCauseService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class SafetyRootCauseServiceImpl implements SafetyRootCauseService{

	@Autowired
	SafetyRootCauseDao dao;

	@Override
	public List<Safety> getSafetyRootCauseList() throws Exception {
		return dao.getSafetyRootCauseList();
	}

	@Override
	public boolean addSafetyRootCause(Safety obj) throws Exception {
		return dao.addSafetyRootCause(obj);
	}

	@Override
	public TrainingType getSafetyRootCauseDetails(TrainingType obj) throws Exception {
		return dao.getSafetyRootCauseDetails(obj);
	}

	@Override
	public boolean updateSafetyRootCause(TrainingType obj) throws Exception {
		return dao.updateSafetyRootCause(obj);
	}

	@Override
	public boolean deleteSafetyRootCause(TrainingType obj) throws Exception {
		return dao.deleteSafetyRootCause(obj);
	}
}
