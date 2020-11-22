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


}
