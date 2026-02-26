package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface StructureTypeDao {

	public List<Safety> getStructureTypesList() throws Exception;

	public boolean addStructureType(Safety obj) throws Exception;

	public TrainingType getStructureTypeDetails(TrainingType obj) throws Exception;

	public boolean updateStructureType(TrainingType obj) throws Exception;

	public boolean deleteStructureType(TrainingType obj) throws Exception;
}
