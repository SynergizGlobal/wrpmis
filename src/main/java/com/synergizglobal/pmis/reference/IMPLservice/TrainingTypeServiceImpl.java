package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.TrainingTypeDao;
import com.synergizglobal.pmis.reference.Iservice.TrainingTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService{

	@Autowired
	TrainingTypeDao dao;

	@Override
	public List<TrainingType> getTrainingTypesList() throws Exception {
		return dao.getTrainingTypesList();
	}

	@Override
	public boolean addTrainingType(TrainingType obj) throws Exception {
		return dao.addTrainingType(obj);
	}

	@Override
	public TrainingType getTrainingTypeDetails(TrainingType obj) throws Exception {
		return dao.getTrainingTypeDetails(obj);
	}

	@Override
	public boolean updateTrainingType(TrainingType obj) throws Exception {
		return dao.updateTrainingType(obj);
	}

	@Override
	public boolean deleteTrainingType(TrainingType obj) throws Exception {
		return dao.deleteTrainingType(obj);
	}

	
}
