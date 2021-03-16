package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UtilityRequirementStageService {
	
	public List<TrainingType> getUtilityRequirementStagesList() throws Exception;

	public boolean addUtilityRequirementStage(TrainingType obj) throws Exception;

}
