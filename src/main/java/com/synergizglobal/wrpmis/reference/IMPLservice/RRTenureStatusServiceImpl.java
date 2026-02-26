package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.RRTenureStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.RRTenureStatusService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class RRTenureStatusServiceImpl implements RRTenureStatusService{

	@Autowired
	RRTenureStatusDao dao;
	
	@Override
	public TrainingType getRRTenureStatusDetails(TrainingType obj) throws Exception {
		return dao.getRRTenureStatusDetails(obj);
	}

	@Override
	public boolean addRRTenureStatus(TrainingType obj) throws Exception {
		return dao.addRRTenureStatus(obj);
	}

	@Override
	public boolean updateRRTenureStatus(TrainingType obj) throws Exception {
		return dao.updateRRTenureStatus(obj);
	}

	@Override
	public boolean deleteRRTenureStatus(TrainingType obj) throws Exception {
		return dao.deleteRRTenureStatus(obj);
	}

}
