package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UtilityExecutionAgencyDao {

	public List<TrainingType> getUtilityExecutionAgencysList() throws Exception;

	public boolean addUtilityExecutionAgency(TrainingType obj) throws Exception;
}
