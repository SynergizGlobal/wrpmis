package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface SafetyCategoryService {
	
	public List<Safety> getSafetyCategoryList() throws Exception;

	public boolean addSafetyCategory(Safety obj) throws Exception;

	public TrainingType getSafetyCategoryDetails(TrainingType obj) throws Exception;

	public boolean updateSafetyCategory(TrainingType obj) throws Exception;

	public boolean deleteSafetyCategory(TrainingType obj) throws Exception;
}
