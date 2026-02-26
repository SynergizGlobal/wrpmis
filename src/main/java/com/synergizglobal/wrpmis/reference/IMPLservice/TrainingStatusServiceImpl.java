package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.TrainingStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.TrainingStatusService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class TrainingStatusServiceImpl implements TrainingStatusService{

	@Autowired
	TrainingStatusDao dao;

	@Override
	public List<TrainingType> getTrainingStatusList() throws Exception {
		return dao.getTrainingStatusList();
	}

	@Override
	public boolean addTrainingStatus(TrainingType obj) throws Exception {
		return dao.addTrainingStatus(obj);
	}

	@Override
	public TrainingType getTrainingStatusDetails(TrainingType obj) throws Exception {
		return dao.getTrainingStatusDetails(obj);
	}

	@Override
	public boolean updateTrainingStatus(TrainingType obj) throws Exception {
		return dao.updateTrainingStatus(obj);
	}

	@Override
	public boolean deleteTrainingStatus(TrainingType obj) throws Exception {
		return dao.deleteTrainingStatus(obj);
	}
}
