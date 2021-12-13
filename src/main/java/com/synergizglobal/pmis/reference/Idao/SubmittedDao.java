package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface SubmittedDao {

	TrainingType getSubmittedDetails(TrainingType obj) throws Exception;

	boolean addSubmitted(TrainingType obj) throws Exception;

	boolean updateSubmitted(TrainingType obj) throws Exception;

	boolean deleteSubmitted(TrainingType obj) throws Exception;

}
