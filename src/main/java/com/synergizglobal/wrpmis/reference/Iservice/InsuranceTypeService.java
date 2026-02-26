package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface InsuranceTypeService {
	
	public List<TrainingType> getInsuranceTypesList() throws Exception;

	public boolean addInsuranceType(TrainingType obj) throws Exception;

	public TrainingType getInsuranceTypesDetails(TrainingType obj) throws Exception;

	public boolean updateInsuranceTypes(TrainingType obj) throws Exception;

	public boolean deleteInsuranceTypes(TrainingType obj) throws Exception;
}
