package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Training;

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

	public List<Training> getUsersList() throws Exception;

	public int uploadTraining(List<Training> trainingsList) throws Exception;


}
