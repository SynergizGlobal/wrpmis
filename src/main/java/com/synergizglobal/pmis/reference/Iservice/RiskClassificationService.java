package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;

public interface RiskClassificationService {
	
	public List<Risk> getRiskClassificationsList() throws Exception;

	public boolean addRiskClassification(Risk obj) throws Exception;
}
