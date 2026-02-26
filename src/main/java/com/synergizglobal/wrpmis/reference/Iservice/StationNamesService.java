package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface StationNamesService {

	public List<TrainingType> getStationNamesList() throws Exception;

	public TrainingType getStationNamesDetails(TrainingType obj) throws Exception;

	public boolean addStationNames(TrainingType obj) throws Exception;

	public boolean updateStationNames(TrainingType obj) throws Exception;

	public boolean deleteStationNames(TrainingType obj) throws Exception;

}
