package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ExecutionStatusDao {
	public List<Safety> getExecutionStatusList() throws Exception;

	public boolean addExecutionStatus(TrainingType obj) throws Exception;

	public TrainingType getExecutionStatusDetails(TrainingType obj) throws Exception;

	public boolean updateExecutionStatus(TrainingType obj) throws Exception;

	public boolean deleteExecutionStatus(TrainingType obj) throws Exception;
}
