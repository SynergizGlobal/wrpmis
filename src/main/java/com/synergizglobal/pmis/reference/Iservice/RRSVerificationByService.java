package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRSVerificationByService {

	TrainingType getRRStatusDetails(TrainingType obj) throws Exception;

	boolean addRRSVerificationBy(TrainingType obj) throws Exception;

	boolean updateRRSVerificationBy(TrainingType obj) throws Exception;

	boolean deleteRRSVerificationBy(TrainingType obj) throws Exception;

}
