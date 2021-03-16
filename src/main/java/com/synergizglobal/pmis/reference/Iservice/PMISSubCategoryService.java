package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface PMISSubCategoryService {

	public List<Safety> getSubCategoryList() throws Exception;

	public boolean addSubCategory(Safety obj) throws Exception;

	public TrainingType getPmisSubCategoryDetails(TrainingType obj) throws Exception;

	public boolean updatePmisSubCategory(TrainingType obj) throws Exception;

	public boolean deletePmisSubCategory(TrainingType obj) throws Exception;
}
