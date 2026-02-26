package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DataGatheringsService {

	public List<TrainingType> getDataGatheringsList() throws Exception;

	public boolean addDataGatherings(TrainingType obj) throws Exception;

	public TrainingType getDataGatheringStatusDetails(TrainingType obj) throws Exception;

	public boolean updateDataGatheringStatus(TrainingType obj) throws Exception;

	public boolean deleteDataGatheringStatus(TrainingType obj) throws Exception;
}
