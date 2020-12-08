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

	@Override
	public List<Training> getStatusList() throws Exception {
		return dao.getStatusList();
	}

	@Override
	public List<Training> getCategoriesList() throws Exception {
		return dao.getCategoriesList();
	}

	@Override
	public List<Training> getTrainingTypesList() throws Exception {
		return dao.getTrainingTypesList();
	}

	@Override
	public Training getTraining(Training obj) throws Exception {
		return dao.getTraining(obj);
	}

	@Override
	public List<Training> getDepartmentsList() throws Exception {
		return dao.getDepartmentsList();
	}

	@Override
	public List<Training> getTrainings(Training obj) throws Exception {
		return dao.getTrainings(obj);
	}

	@Override
	public List<Training> getIssueCatogoriesList() throws Exception {
		return dao.getIssueCatogoriesList();
	}

	@Override
	public boolean updateTraining(Training obj) throws Exception {
		return dao.updateTraining(obj);
	}

	@Override
	public boolean addTraining(Training obj) throws Exception {
		return dao.addTraining(obj);
	}

	@Override
	public List<Training> getUsersList() throws Exception {
		return dao.getUsersList();
	}

	@Override
	public int uploadTraining(List<Training> trainingsList) throws Exception {
		return dao.uploadTraining(trainingsList);
	}
}
