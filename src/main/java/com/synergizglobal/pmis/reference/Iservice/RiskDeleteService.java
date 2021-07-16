package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RiskDeleteService {

	List<TrainingType> getRisksList() throws Exception;

	boolean deleteRisk(TrainingType obj) throws Exception;

}
