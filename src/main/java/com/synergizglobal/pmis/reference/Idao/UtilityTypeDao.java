package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UtilityTypeDao {


	public List<TrainingType> getUtilityTypesList() throws Exception;

	public boolean addUtilityType(TrainingType obj) throws Exception;
}

