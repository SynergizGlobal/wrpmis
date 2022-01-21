package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.model.WorkFeatures;

public interface SalientFeaturesCategoryDao {
	public WorkFeatures getCategoryDetails(WorkFeatures obj) throws Exception;

	public boolean addCategory(WorkFeatures obj) throws Exception;

	public boolean updateCategory(WorkFeatures obj) throws Exception;

	public boolean deleteCategory(WorkFeatures obj) throws Exception;
}
