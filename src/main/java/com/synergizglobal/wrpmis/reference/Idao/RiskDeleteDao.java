package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RiskDeleteDao {

	List<TrainingType> getRisksList(TrainingType obj) throws Exception;

	boolean deleteRisk(TrainingType obj) throws Exception;

	List<TrainingType> getSubWorkFilterList(TrainingType obj) throws Exception;

}
