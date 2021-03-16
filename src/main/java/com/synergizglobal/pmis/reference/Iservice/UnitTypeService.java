package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UnitTypeService {

	public List<TrainingType> getUnitTypesList() throws Exception;

	public boolean addUnitType(TrainingType obj) throws Exception;
}
