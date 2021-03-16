package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RRStatusDao;
import com.synergizglobal.pmis.reference.Iservice.RRStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RRStatusServiceImpl implements RRStatusService{
	@Autowired
	RRStatusDao dao;

	@Override
	public List<Safety> getRRStatusList() throws Exception {
		return dao.getRRStatusList();
	}

	@Override
	public boolean addRRStatus(Safety obj) throws Exception {
		return dao.addRRStatus(obj);
	}

	@Override
	public TrainingType getRRStatusDetails(TrainingType obj) throws Exception {
		return dao.getRRStatusDetails(obj);
	}

	@Override
	public boolean updateRRStatus(TrainingType obj) throws Exception {
		return dao.updateRRStatus(obj);
	}

	@Override
	public boolean deleteRRStatus(TrainingType obj) throws Exception {
		return dao.deleteRRStatus(obj);
	}
}
