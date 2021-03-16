package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TrainingStatusService {

	public List<TrainingType> getTrainingStatusList() throws Exception;

	public boolean addTrainingStatus(TrainingType obj) throws Exception;

	public TrainingType getTrainingStatusDetails(TrainingType obj) throws Exception;

	public boolean updateTrainingStatus(TrainingType obj) throws Exception;

	public boolean deleteTrainingStatus(TrainingType obj) throws Exception;
}
