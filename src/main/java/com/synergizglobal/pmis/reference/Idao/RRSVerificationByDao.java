package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRSVerificationByDao {

	TrainingType getRRStatusDetails(TrainingType obj) throws Exception;

	boolean addRRSVerificationBy(TrainingType obj) throws Exception;

	boolean updateRRSVerificationBy(TrainingType obj) throws Exception;

	boolean deleteRRSVerificationBy(TrainingType obj) throws Exception;

}
