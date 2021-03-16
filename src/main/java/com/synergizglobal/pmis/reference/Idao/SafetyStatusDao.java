package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface SafetyStatusDao {

	public List<Safety> getSafetyStatusList() throws Exception;

	public boolean addSafetyStatus(Safety obj) throws Exception;

	public TrainingType getSafetyStatusDetails(TrainingType obj) throws Exception;

	public boolean updateSafetyStatus(TrainingType obj) throws Exception;

	public boolean deleteSafetyStatus(TrainingType obj) throws Exception;
	
}
