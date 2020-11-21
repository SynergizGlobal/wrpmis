package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.TrainingDao;
import com.synergizglobal.pmis.Iservice.TrainingService;
import com.synergizglobal.pmis.model.Training;

@Service
public class TrainingServiceImpl implements TrainingService{

	@Autowired
	TrainingDao dao;

	@Override
	public List<Training> getTrainingList(Training obj) throws Exception {
		return dao.getTrainingList(obj);
	}

	@Override
	public List<Training> getTrainingTypesList(Training obj) throws Exception {
		return dao.getTrainingTypesList(obj);
	}

	@Override
	public List<Training> getTrainingCategorysList(Training obj) throws Exception {
		return dao.getTrainingCategorysList(obj);
	}

	@Override
	public List<Training> getStatusList(Training obj) throws Exception {
		return dao.getStatusList(obj);
	}
}
