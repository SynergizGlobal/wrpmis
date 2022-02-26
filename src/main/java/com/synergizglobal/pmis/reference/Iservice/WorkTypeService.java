package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface WorkTypeService {

	TrainingType getWorkTypeDetails(TrainingType obj) throws Exception;

	boolean addWorkType(TrainingType obj) throws Exception;

	boolean updateWorkType(TrainingType obj) throws Exception;

	boolean deleteWorkType(TrainingType obj) throws Exception;
}
