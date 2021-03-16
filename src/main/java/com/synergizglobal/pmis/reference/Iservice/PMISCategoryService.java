package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface PMISCategoryService {

	public List<Safety> getCategoryList() throws Exception;

	public boolean addCategory(Safety obj) throws Exception;

	public TrainingType getPmisCategoryDetails(TrainingType obj) throws Exception;

	public boolean updatePmisCategory(TrainingType obj) throws Exception;

	public boolean deletePmisCategory(TrainingType obj) throws Exception;
}
