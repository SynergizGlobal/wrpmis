package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface StageService {

	TrainingType getStageDetails(TrainingType obj) throws Exception;

	boolean addStage(TrainingType obj) throws Exception;

	boolean updateStage(TrainingType obj) throws Exception;

	boolean deleteStage(TrainingType obj) throws Exception;

}
