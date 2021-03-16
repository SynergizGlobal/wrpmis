package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RiskSubAreaDao {

	public List<Risk> getRiskSubAreasList() throws Exception;

	public boolean addRiskSubArea(Risk obj) throws Exception;

	public TrainingType getRiskSubAreaDetails(TrainingType obj) throws Exception;

	public List<TrainingType> getRiskAreaList(TrainingType obj) throws Exception;

	public boolean updateRiskSubArea(TrainingType obj) throws Exception;

	public boolean deleteRiskSubArea(TrainingType obj) throws Exception;
}
