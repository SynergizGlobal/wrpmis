package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface SafetyImpactService {
	
	public List<Safety> getSafetyImpactsList() throws Exception;

	public boolean addSafetyImpact(Safety obj) throws Exception;

	public TrainingType getSafetyImpactDetails(TrainingType obj) throws Exception;

	public boolean updateSafetyImpact(TrainingType obj) throws Exception;

	public boolean deleteSafetyImpact(TrainingType obj) throws Exception;
}
