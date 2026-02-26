package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.model.WorkFeatures;
import com.synergizglobal.wrpmis.reference.Idao.SalientFeaturesCategoryDao;
import com.synergizglobal.wrpmis.reference.Iservice.SalientFeaturesCategoryService;

@Service
public class SalientFeaturesCategoryServiceImpl implements SalientFeaturesCategoryService{
	
	@Autowired
	SalientFeaturesCategoryDao dao;

	@Override
	public WorkFeatures getCategoryDetails(WorkFeatures obj) throws Exception {
		return dao.getCategoryDetails(obj);
	}

	@Override
	public boolean addCategory(WorkFeatures obj) throws Exception {
		return dao.addCategory(obj);
	}

	@Override
	public boolean updateCategory(WorkFeatures obj) throws Exception {
		return dao.updateCategory(obj);
	}

	@Override
	public boolean deleteCategory(WorkFeatures obj) throws Exception {
		return dao.deleteCategory(obj);
	}

}
