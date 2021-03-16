package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TrainingCategoryService {


	public List<TrainingType> getTrainingCategoriesList() throws Exception;

	public boolean addTrainingCategory(TrainingType obj) throws Exception;

	public TrainingType getTrainingCategoriesDetails(TrainingType obj) throws Exception;

	public boolean updateTrainingCategories(TrainingType obj) throws Exception;

	public boolean deleteTrainingCategories(TrainingType obj) throws Exception;
}
