package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface PMISCategoryDao {

	public List<Safety> getCategoryList() throws Exception;

	public boolean addCategory(Safety obj) throws Exception;

	public TrainingType getPmisCategoryDetails(TrainingType obj) throws Exception;

	public boolean updatePmisCategory(TrainingType obj) throws Exception;

	public boolean deletePmisCategory(TrainingType obj) throws Exception;
}
