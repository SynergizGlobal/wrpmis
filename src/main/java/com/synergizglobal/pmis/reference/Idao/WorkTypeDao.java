package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface WorkTypeDao {

	TrainingType getWorkTypeDetails(TrainingType obj) throws Exception;

	boolean addWorkType(TrainingType obj) throws Exception;

	boolean updateWorkType(TrainingType obj) throws Exception;

	boolean deleteWorkType(TrainingType obj) throws Exception;

}
