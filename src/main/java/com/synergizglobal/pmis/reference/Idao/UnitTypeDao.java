package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UnitTypeDao {

	public List<TrainingType> getUnitTypesList() throws Exception;

	public boolean addUnitType(TrainingType obj) throws Exception;
}
