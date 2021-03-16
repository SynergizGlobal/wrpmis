package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RiskPriorityDao {

	public List<Safety> getRiskPriorityList() throws Exception;

	public boolean addRiskPriority(Safety obj) throws Exception;

	public TrainingType getRiskPriorityDetails(TrainingType obj) throws Exception;

	public boolean updateRiskPriority(TrainingType obj) throws Exception;

	public boolean deleteRiskPriority(TrainingType obj) throws Exception;

}
