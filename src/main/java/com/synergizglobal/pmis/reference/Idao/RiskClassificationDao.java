package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RiskClassificationDao {

	public List<Risk> getRiskClassificationsList() throws Exception;

	public boolean addRiskClassification(Risk obj) throws Exception;

	public TrainingType getRiskClassificationDetails(TrainingType obj) throws Exception;

	public boolean updateRiskClassification(TrainingType obj) throws Exception;

	public boolean deleteRiskClassification(TrainingType obj) throws Exception;
}
