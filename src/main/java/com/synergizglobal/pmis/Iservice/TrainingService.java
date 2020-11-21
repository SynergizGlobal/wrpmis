package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Training;

public interface TrainingService {

	public List<Training> getTrainingList(Training obj) throws Exception;

	public List<Training> getTrainingTypesList(Training obj) throws Exception;

	public List<Training> getTrainingCategorysList(Training obj) throws Exception;

	public List<Training> getStatusList(Training obj) throws Exception;

}
