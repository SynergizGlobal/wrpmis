package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.RRBoundarywallStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.RRBoundarywallStatusService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class RRBoundarywallStatusServiceImpl implements RRBoundarywallStatusService{
	
	@Autowired 
	RRBoundarywallStatusDao dao;
	@Override
	public TrainingType getRRBoundarywallStatusDetails(TrainingType obj) throws Exception {
		return dao.getRRBoundarywallStatusDetails(obj);
	}

	@Override
	public boolean addRRBoundarywallStatus(TrainingType obj) throws Exception {
		return dao.addRRBoundarywallStatus(obj);
	}

	@Override
	public boolean updateRRBoundarywallStatus(TrainingType obj) throws Exception {
		return dao.updateRRBoundarywallStatus(obj);
	}

	@Override
	public boolean deleteRRBoundarywallStatus(TrainingType obj) throws Exception {
		return dao.deleteRRBoundarywallStatus(obj);
	}

}
