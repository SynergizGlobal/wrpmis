package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SoftDeleteStatusDao;
import com.synergizglobal.pmis.reference.Iservice.SoftDeleteStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class SoftDeleteStatusServiceImpl implements SoftDeleteStatusService{
	@Autowired
	SoftDeleteStatusDao dao;

	@Override
	public List<TrainingType> getSoftDeleteStatussList() throws Exception {
		return dao.getSoftDeleteStatussList();
	}

	@Override
	public boolean addSoftDeleteStatus(TrainingType obj) throws Exception {
		return dao.addSoftDeleteStatus(obj);
	}

	@Override
	public TrainingType getSoftDeleteStatusDetails(TrainingType obj) throws Exception {
		return dao.getSoftDeleteStatusDetails(obj);
	}

	@Override
	public boolean updateSoftDeleteStatus(TrainingType obj) throws Exception {
		return dao.updateSoftDeleteStatus(obj);
	}

	@Override
	public boolean deleteSoftDeleteStatus(TrainingType obj) throws Exception {
		return dao.deleteSoftDeleteStatus(obj);
	}
}
