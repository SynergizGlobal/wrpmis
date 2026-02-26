package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.model.WorkFeatures;

public interface SalientFeaturesCategoryService {
	public WorkFeatures getCategoryDetails(WorkFeatures obj) throws Exception;

	public boolean addCategory(WorkFeatures obj) throws Exception;

	public boolean updateCategory(WorkFeatures obj) throws Exception;

	public boolean deleteCategory(WorkFeatures obj) throws Exception;
}
