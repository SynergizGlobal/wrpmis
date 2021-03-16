package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TrainingStatusDao {


	public List<TrainingType> getTrainingStatusList() throws Exception;

	public boolean addTrainingStatus(TrainingType obj) throws Exception;

	public TrainingType getTrainingStatusDetails(TrainingType obj) throws Exception;

	public boolean updateTrainingStatus(TrainingType obj) throws Exception;

	public boolean deleteTrainingStatus(TrainingType obj) throws Exception;

}
