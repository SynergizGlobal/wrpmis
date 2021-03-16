package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RiskAreaDao {

	public List<Risk> getRiskAreasList() throws Exception;

	public boolean addRiskArea(Risk obj) throws Exception;

	public TrainingType getRiskAreaDetails(TrainingType obj) throws Exception;

	public boolean updateRiskArea(TrainingType obj) throws Exception;

	public boolean deleteRiskArea(TrainingType obj) throws Exception;
}
