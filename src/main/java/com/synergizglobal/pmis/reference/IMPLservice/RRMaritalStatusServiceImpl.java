package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RRMaritalStatusDao;
import com.synergizglobal.pmis.reference.Iservice.RRMaritalStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class RRMaritalStatusServiceImpl implements RRMaritalStatusService{
	@Autowired
	RRMaritalStatusDao dao;

	@Override
	public TrainingType getRRMaritalStatusDetails(TrainingType obj) throws Exception {
		return dao.getRRMaritalStatusDetails(obj);
	}
	
	@Override
	public boolean addRRMaritalStatus(TrainingType obj) throws Exception {
		return dao.addRRMaritalStatus(obj);
	}

	@Override
	public boolean updateRRMaritalStatus(TrainingType obj) throws Exception {
		return dao.updateRRMaritalStatus(obj);
	}

	@Override
	public boolean deleteRRMaritalStatus(TrainingType obj) throws Exception {
		return dao.deleteRRMaritalStatus(obj);
	}

}
