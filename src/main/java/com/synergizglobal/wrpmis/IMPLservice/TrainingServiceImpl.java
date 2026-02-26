package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.TrainingDao;
import com.synergizglobal.wrpmis.Iservice.TrainingService;
import com.synergizglobal.wrpmis.model.Training;

@Service
public class TrainingServiceImpl implements TrainingService {

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
	public List<Training> getTrainingTitlesList(Training obj) throws Exception {
		return dao.getTrainingTitlesList(obj);
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
	public List<Training> getUsersList(Training obj) throws Exception {
		return dao.getUsersList(obj);
	}

	@Override
	public int uploadTraining(List<Training> trainingsList) throws Exception {
		return dao.uploadTraining(trainingsList);
	}

	@Override
	public List<Training> getTrainingSessionsList(String id) throws Exception {
		return dao.getTrainingSessionsList(id);
	}

	@Override
	public List<Training> getTrainingAttendeesList(String trainingId) throws Exception {
		return dao.getTrainingAttendeesList(trainingId);
	}

	@Override
	public List<Training> getAttendeesList(Training obj) throws Exception {
		return dao.getAttendeesList(obj);
	}

	@Override
	public int getTotalRecords(Training obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj, searchParameter);
	}

	@Override
	public List<Training> getperiodicityList() throws Exception {

		return dao.getperiodicityList();
	}

	@Override
	public List<Training> getprovidedList() throws Exception {

		return dao.getprovidedList();
	}

	@Override
	public List<Training> gettraining_ConductedList() throws Exception {

		return dao.gettraining_ConductedList();
	}

	@Override
	public List<Training> getcontract_short_nameList() throws Exception {

		return dao.getcontract_short_nameList();
	}

	@Override
	public List<Training> getTrainingsList(Training obj) throws Exception {
		return dao.getTrainingsList(obj);
	}

	@Override
	public List<Training> getTrainingsList(Training obj, int startIndex, int offset, String searchParameter)
			throws Exception {

		return dao.getTrainingsList(obj);
	}
}
