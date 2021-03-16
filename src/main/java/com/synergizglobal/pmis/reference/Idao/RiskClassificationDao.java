package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;

public interface RiskClassificationDao {

	public List<Risk> getRiskClassificationsList() throws Exception;

	public boolean addRiskClassification(Risk obj) throws Exception;
}
