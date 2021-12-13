package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface PurposeOfSubmissionService {

	TrainingType getPurposeOfSubmissionDetails(TrainingType obj) throws Exception;

	boolean addPurposeOfSubmission(TrainingType obj) throws Exception;

	boolean updatePurposeOfSubmission(TrainingType obj) throws Exception;

	boolean deletePurposeOfSubmission(TrainingType obj) throws Exception;

}
