package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Training;

public interface TrainingDao {

	public List<Training> getTrainingList(Training obj) throws Exception;

	public List<Training> getTrainingTypesList(Training obj) throws Exception;

	public List<Training> getTrainingCategorysList(Training obj) throws Exception;

	public List<Training> getStatusList(Training obj) throws Exception;

	public List<Training> getStatusList() throws Exception;

	public List<Training> getCategoriesList() throws Exception;

	public List<Training> getTrainingTypesList() throws Exception;

	public Training getTraining(Training obj) throws Exception;

	public List<Training> getDepartmentsList() throws Exception;

	public List<Training> getTrainings(Training obj) throws Exception;

	public List<Training> getIssueCatogoriesList() throws Exception;

	public boolean updateTraining(Training obj) throws Exception;

	public boolean addTraining(Training obj) throws Exception;

	public List<Training> getUsersList(Training obj) throws Exception;

	public int uploadTraining(List<Training> trainingsList) throws Exception;

	public List<Training> getTrainingSessionsList(String id) throws Exception;

	public List<Training> getTrainingAttendeesList(String trainingId) throws Exception;

	public List<Training> getAttendeesList(Training obj) throws Exception;

	public int getTotalRecords(Training obj, String searchParameter) throws Exception;

	public List<Training> getTrainingsList(Training obj) throws Exception;

	/*
	 * public List<Training> getTrainingsList(Training obj, int startIndex, int
	 * offset, String searchParameter) throws Exception;
	 */

	public List<Training> getTrainingTitlesList(Training obj) throws Exception;

	public List<Training> getperiodicityList() throws Exception;

	public List<Training> getprovidedList() throws Exception;

	public List<Training> gettraining_ConductedList() throws Exception;

	public List<Training> getcontract_short_nameList() throws Exception;

}
