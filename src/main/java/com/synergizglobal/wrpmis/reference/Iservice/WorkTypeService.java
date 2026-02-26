package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface WorkTypeService {

	TrainingType getWorkTypeDetails(TrainingType obj) throws Exception;

	boolean addWorkType(TrainingType obj) throws Exception;

	boolean updateWorkType(TrainingType obj) throws Exception;

	boolean deleteWorkType(TrainingType obj) throws Exception;
}
