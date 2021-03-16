package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UtilityRequirementStageDao {

	public List<TrainingType> getUtilityRequirementStagesList() throws Exception;

	public boolean addUtilityRequirementStage(TrainingType obj) throws Exception;
}
