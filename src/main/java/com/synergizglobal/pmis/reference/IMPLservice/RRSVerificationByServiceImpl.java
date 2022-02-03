package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.AlertLevelDao;
import com.synergizglobal.pmis.reference.Idao.RRSVerificationByDao;
import com.synergizglobal.pmis.reference.Iservice.RRSVerificationByService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class RRSVerificationByServiceImpl implements RRSVerificationByService{
	@Autowired
	RRSVerificationByDao dao;

	@Override
	public TrainingType getRRStatusDetails(TrainingType obj) throws Exception {
		return dao.getRRStatusDetails(obj);
	}

	@Override
	public boolean addRRSVerificationBy(TrainingType obj) throws Exception {
		return dao.addRRSVerificationBy(obj);
	}

	@Override
	public boolean updateRRSVerificationBy(TrainingType obj) throws Exception {
		return dao.updateRRSVerificationBy(obj);
	}

	@Override
	public boolean deleteRRSVerificationBy(TrainingType obj) throws Exception {
		return dao.deleteRRSVerificationBy(obj);
	}
}
