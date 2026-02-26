package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RiskClassificationService {
	
	public List<Risk> getRiskClassificationsList() throws Exception;

	public boolean addRiskClassification(Risk obj) throws Exception;

	public TrainingType getRiskClassificationDetails(TrainingType obj) throws Exception;

	public boolean updateRiskClassification(TrainingType obj) throws Exception;

	public boolean deleteRiskClassification(TrainingType obj) throws Exception;
}
