package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UtilityExecutionAgencyService {

	public List<TrainingType> getUtilityExecutionAgencysList() throws Exception;

	public boolean addUtilityExecutionAgency(TrainingType obj) throws Exception;

}
