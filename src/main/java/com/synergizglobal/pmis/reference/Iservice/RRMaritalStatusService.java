package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRMaritalStatusService {

	boolean addRRMaritalStatus(TrainingType obj) throws Exception;

	boolean updateRRMaritalStatus(TrainingType obj) throws Exception;

	boolean deleteRRMaritalStatus(TrainingType obj) throws Exception;

	TrainingType getRRMaritalStatusDetails(TrainingType obj) throws Exception;

}
