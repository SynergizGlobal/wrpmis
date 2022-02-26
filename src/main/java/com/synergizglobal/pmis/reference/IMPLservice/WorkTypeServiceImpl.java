package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.WorkTypeDao;
import com.synergizglobal.pmis.reference.Iservice.WorkTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class WorkTypeServiceImpl implements WorkTypeService{
	@Autowired
	WorkTypeDao dao;

	@Override
	public TrainingType getWorkTypeDetails(TrainingType obj) throws Exception {
		return dao.getWorkTypeDetails(obj);
	}

	@Override
	public boolean addWorkType(TrainingType obj) throws Exception {
		return dao.addWorkType(obj);
	}

	@Override
	public boolean updateWorkType(TrainingType obj) throws Exception {
		return dao.updateWorkType(obj);
	}

	@Override
	public boolean deleteWorkType(TrainingType obj) throws Exception {
		return dao.deleteWorkType(obj);
	}
}
