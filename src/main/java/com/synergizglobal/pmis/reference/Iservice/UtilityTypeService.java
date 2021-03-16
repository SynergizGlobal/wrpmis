package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UtilityTypeService {

	public List<TrainingType> getUtilityTypesList() throws Exception;

	public boolean addUtilityType(TrainingType obj) throws Exception;
}
