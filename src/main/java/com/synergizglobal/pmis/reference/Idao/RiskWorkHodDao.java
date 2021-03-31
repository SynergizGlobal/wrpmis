package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RiskWorkHodDao {

	public List<TrainingType> getRiskWorkHODDetails(TrainingType obj) throws Exception;

	public List<TrainingType> getWorkDetails(TrainingType obj) throws Exception;

	public List<TrainingType> getHODDetails(TrainingType obj) throws Exception;

	public boolean addRiskWorkHOD(TrainingType obj) throws Exception;

	public boolean updateRiskWorkHOD(TrainingType obj) throws Exception;

	public boolean deleteRiskWorkHOD(TrainingType obj) throws Exception;

}
