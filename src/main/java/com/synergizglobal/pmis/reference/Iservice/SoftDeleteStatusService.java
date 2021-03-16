package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface SoftDeleteStatusService {
	
	public List<TrainingType> getSoftDeleteStatussList() throws Exception;

	public boolean addSoftDeleteStatus(TrainingType obj) throws Exception;

	public TrainingType getSoftDeleteStatusDetails(TrainingType obj) throws Exception;

	public boolean updateSoftDeleteStatus(TrainingType obj) throws Exception;

	public boolean deleteSoftDeleteStatus(TrainingType obj) throws Exception;
}
