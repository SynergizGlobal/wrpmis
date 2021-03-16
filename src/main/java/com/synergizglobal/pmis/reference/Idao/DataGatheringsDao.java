package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface DataGatheringsDao {

	public List<TrainingType> getDataGatheringsList() throws Exception;

	public boolean addDataGatherings(TrainingType obj) throws Exception;

	public TrainingType getDataGatheringStatusDetails(TrainingType obj) throws Exception;

	public boolean updateDataGatheringStatus(TrainingType obj) throws Exception;

	public boolean deleteDataGatheringStatus(TrainingType obj) throws Exception;
}
