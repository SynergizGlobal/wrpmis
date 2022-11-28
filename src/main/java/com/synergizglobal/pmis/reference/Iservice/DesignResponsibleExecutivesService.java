package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface DesignResponsibleExecutivesService {

	List<TrainingType> getExecutivesDetails(TrainingType obj) throws Exception;

	boolean addDesignExecutives(TrainingType obj) throws Exception;

	boolean updateDesignExecutives(TrainingType obj) throws Exception;

	List<TrainingType> getWorkDetails(TrainingType obj) throws Exception;
	
	List<TrainingType> getUsersDetails(TrainingType obj) throws Exception;

}
