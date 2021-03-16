package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.TrainingCategoryDao;
import com.synergizglobal.pmis.reference.Iservice.TrainingCategoryService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class TrainingCategoryServiceImpl implements TrainingCategoryService{

	@Autowired
	TrainingCategoryDao dao;


	@Override
	public List<TrainingType> getTrainingCategoriesList() throws Exception {
		return dao.getTrainingCategoriesList();
	}

	@Override
	public boolean addTrainingCategory(TrainingType obj) throws Exception {
		return dao.addTrainingCategory(obj);
	}

	@Override
	public TrainingType getTrainingCategoriesDetails(TrainingType obj) throws Exception {
		return dao.getTrainingCategoriesDetails(obj);
	}

	@Override
	public boolean updateTrainingCategories(TrainingType obj) throws Exception {
		return dao.updateTrainingCategories(obj);
	}

	@Override
	public boolean deleteTrainingCategories(TrainingType obj) throws Exception {
		return dao.deleteTrainingCategories(obj);
	}
}
