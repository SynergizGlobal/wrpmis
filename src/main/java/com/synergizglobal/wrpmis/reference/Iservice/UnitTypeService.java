package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface UnitTypeService {

	public List<TrainingType> getUnitTypesList() throws Exception;

	public boolean addUnitType(TrainingType obj) throws Exception;
}
