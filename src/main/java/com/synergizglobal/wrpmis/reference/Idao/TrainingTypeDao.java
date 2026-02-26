package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface TrainingTypeDao {

	public List<TrainingType> getTrainingTypesList() throws Exception;

	public boolean addTrainingType(TrainingType obj) throws Exception;

	public TrainingType getTrainingTypeDetails(TrainingType obj) throws Exception;

	public boolean updateTrainingType(TrainingType obj) throws Exception;

	public boolean deleteTrainingType(TrainingType obj) throws Exception;


}
