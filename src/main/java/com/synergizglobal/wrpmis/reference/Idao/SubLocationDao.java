package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface SubLocationDao {

	List<TrainingType> getSubLocationsList() throws Exception;

	List<TrainingType> getLocationList() throws Exception;

	TrainingType getSubLocationDetails(TrainingType obj) throws Exception;

	boolean addSubLocation(TrainingType obj) throws Exception;

	boolean updateSubLocation(TrainingType obj) throws Exception;

	boolean deleteSubLocation(TrainingType obj) throws Exception;

	List<TrainingType> getSubLocations(TrainingType obj) throws Exception;

}
