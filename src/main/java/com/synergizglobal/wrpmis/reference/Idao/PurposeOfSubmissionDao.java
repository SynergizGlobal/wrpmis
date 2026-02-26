package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface PurposeOfSubmissionDao {

	TrainingType getPurposeOfSubmissionDetails(TrainingType obj) throws Exception;

	boolean addPurposeOfSubmission(TrainingType obj) throws Exception;

	boolean updatePurposeOfSubmission(TrainingType obj) throws Exception;

	boolean deletePurposeOfSubmission(TrainingType obj) throws Exception;

}
