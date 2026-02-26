package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.SubmittedDao;
import com.synergizglobal.wrpmis.reference.Iservice.SubmittedService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class SubmittedServiceimpl implements SubmittedService{

	@Autowired
	SubmittedDao dao;

	@Override
	public TrainingType getSubmittedDetails(TrainingType obj) throws Exception {
		return dao.getSubmittedDetails(obj);
	}

	@Override
	public boolean addSubmitted(TrainingType obj) throws Exception {
		return dao.addSubmitted(obj);
	}

	@Override
	public boolean updateSubmitted(TrainingType obj) throws Exception {
		return dao.updateSubmitted(obj);
	}

	@Override
	public boolean deleteSubmitted(TrainingType obj) throws Exception {
		return dao.deleteSubmitted(obj);
	}
}
