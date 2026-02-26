package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RrResponsibleExecutivesService {

	List<TrainingType> getExecutivesDetails(TrainingType obj) throws Exception;

	boolean addRRExecutives(TrainingType obj) throws Exception;

	boolean updateRRExecutives(TrainingType obj) throws Exception;

	List<TrainingType> executivesList(TrainingType obj) throws Exception;

	List<TrainingType> getWorkDetails(TrainingType obj) throws Exception;

	List<TrainingType> getUsersDetails(TrainingType obj) throws Exception;

}
