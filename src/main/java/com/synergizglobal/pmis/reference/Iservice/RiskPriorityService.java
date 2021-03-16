package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RiskPriorityService {

	public List<Safety> getRiskPriorityList() throws Exception;

	public boolean addRiskPriority(Safety obj) throws Exception;

	public TrainingType getRiskPriorityDetails(TrainingType obj) throws Exception;

	public boolean updateRiskPriority(TrainingType obj) throws Exception;

	public boolean deleteRiskPriority(TrainingType obj) throws Exception;
}
