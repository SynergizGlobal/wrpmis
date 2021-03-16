package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface InsuranceTypeDao {

	public List<TrainingType> getInsuranceTypesList() throws Exception;

	public boolean addInsuranceType(TrainingType obj) throws Exception;

	public TrainingType getInsuranceTypesDetails(TrainingType obj) throws Exception;

	public boolean updateInsuranceTypes(TrainingType obj) throws Exception;

	public boolean deleteInsuranceTypes(TrainingType obj) throws Exception;
}
