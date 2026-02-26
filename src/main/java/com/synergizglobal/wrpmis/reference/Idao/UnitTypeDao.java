package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface UnitTypeDao {

	public List<TrainingType> getUnitTypesList() throws Exception;

	public boolean addUnitType(TrainingType obj) throws Exception;
}
