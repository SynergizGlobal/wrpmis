package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.AlertLevelDao;
import com.synergizglobal.wrpmis.reference.Idao.RRSVerificationByDao;
import com.synergizglobal.wrpmis.reference.Iservice.RRSVerificationByService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

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
