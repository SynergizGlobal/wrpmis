package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RRLandTypeDao;
import com.synergizglobal.pmis.reference.Iservice.RRLandTypeService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RRLandTypeServiceImpl implements RRLandTypeService{

	@Autowired
	RRLandTypeDao dao;

	@Override
	public List<Safety> getRRLandTypeList() throws Exception {
		return dao.getRRLandTypeList();
	}

	@Override
	public boolean addRRLandType(Safety obj) throws Exception {
		return dao.addRRLandType(obj);
	}

	@Override
	public TrainingType getRRLandTypeDetails(TrainingType obj) throws Exception {
		return dao.getRRLandTypeDetails(obj);
	}

	@Override
	public boolean updateRRLandType(TrainingType obj) throws Exception {
		return dao.updateRRLandType(obj);
	}

	@Override
	public boolean deleteRRLandType(TrainingType obj) throws Exception {
		return dao.deleteRRLandType(obj);
	}
}
