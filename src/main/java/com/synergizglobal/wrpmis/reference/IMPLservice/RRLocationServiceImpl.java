package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.RRLocationDao;
import com.synergizglobal.wrpmis.reference.Iservice.RRLocationService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class RRLocationServiceImpl implements RRLocationService{
	@Autowired
	RRLocationDao dao;
	
	@Override
	public TrainingType getRRLocationDetails(TrainingType obj) throws Exception {
		return dao.getRRLocationDetails(obj);
	}

	@Override
	public boolean addRRLocation(TrainingType obj) throws Exception {
		return dao.addRRLocation(obj);
	}

	@Override
	public boolean deleteRRLocation(TrainingType obj) throws Exception {
		return dao.deleteRRLocation(obj);
	}

	@Override
	public boolean updateRRLocation(TrainingType obj) throws Exception {
		return dao.updateRRLocation(obj);
	}

}
