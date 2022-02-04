package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RRTypeOfUseDao;
import com.synergizglobal.pmis.reference.Iservice.RRTypeOfUseService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class RRTypeOfUseServiceImpl implements RRTypeOfUseService {
	@Autowired
	RRTypeOfUseDao dao;
	
	@Override
	public TrainingType getRRTypeOfUseDetails(TrainingType obj) throws Exception {
		return dao.getRRTypeOfUseDetails(obj);
	}

	@Override
	public boolean addRRTypeOfUse(TrainingType obj) throws Exception {
		return dao.addRRTypeOfUse(obj);
	}

	@Override
	public boolean updateRRTypeOfUse(TrainingType obj) throws Exception {
		return dao.updateRRTypeOfUse(obj);
	}

	@Override
	public boolean deleteRRTypeOfUse(TrainingType obj) throws Exception {
		return dao.deleteRRTypeOfUse(obj);
	}

}
