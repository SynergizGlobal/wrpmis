package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface SoftDeleteStatusDao {

	public List<TrainingType> getSoftDeleteStatussList() throws Exception;

	public boolean addSoftDeleteStatus(TrainingType obj) throws Exception;

	public TrainingType getSoftDeleteStatusDetails(TrainingType obj) throws Exception;

	public boolean updateSoftDeleteStatus(TrainingType obj) throws Exception;

	public boolean deleteSoftDeleteStatus(TrainingType obj) throws Exception;
}
