package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TrainingCategoryDao {


	public List<TrainingType> getTrainingCategoriesList() throws Exception;

	public boolean addTrainingCategory(TrainingType obj) throws Exception;

	public TrainingType getTrainingCategoriesDetails(TrainingType obj) throws Exception;

	public boolean updateTrainingCategories(TrainingType obj) throws Exception;

	public boolean deleteTrainingCategories(TrainingType obj) throws Exception;

}
