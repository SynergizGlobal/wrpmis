package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RiskDeleteService {

	List<TrainingType> getRisksList(TrainingType obj) throws Exception;

	boolean deleteRisk(TrainingType obj) throws Exception;

	List<TrainingType> getSubWorkFilterList(TrainingType obj) throws Exception;

}
