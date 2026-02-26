package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RiskSubAreaDao {

	public List<Risk> getRiskSubAreasList() throws Exception;

	public boolean addRiskSubArea(Risk obj) throws Exception;

	public TrainingType getRiskSubAreaDetails(TrainingType obj) throws Exception;

	public List<TrainingType> getRiskAreaList(TrainingType obj) throws Exception;

	public boolean updateRiskSubArea(TrainingType obj) throws Exception;

	public boolean deleteRiskSubArea(TrainingType obj) throws Exception;

	public List<TrainingType> getSubAreaDetails(TrainingType obj) throws Exception;
}
