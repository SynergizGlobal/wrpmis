package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRSVerificationByDao {

	TrainingType getRRStatusDetails(TrainingType obj) throws Exception;

	boolean addRRSVerificationBy(TrainingType obj) throws Exception;

	boolean updateRRSVerificationBy(TrainingType obj) throws Exception;

	boolean deleteRRSVerificationBy(TrainingType obj) throws Exception;

}
