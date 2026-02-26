package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface SubmittedService {

	TrainingType getSubmittedDetails(TrainingType obj) throws Exception;

	boolean addSubmitted(TrainingType obj) throws Exception;

	boolean updateSubmitted(TrainingType obj) throws Exception;

	boolean deleteSubmitted(TrainingType obj) throws Exception;

}
