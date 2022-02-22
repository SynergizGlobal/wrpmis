package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface LandResponsibleExecutivesDao {

	List<TrainingType> getExecutivesDetails(TrainingType obj) throws Exception;

	boolean addLandAcquisitionExecutives(TrainingType obj) throws Exception;

	boolean updateLandAcquisitionExecutives(TrainingType obj) throws Exception;

}
