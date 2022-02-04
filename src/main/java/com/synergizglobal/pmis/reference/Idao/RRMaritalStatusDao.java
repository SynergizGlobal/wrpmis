package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRMaritalStatusDao {

	TrainingType getRRMaritalStatusDetails(TrainingType obj) throws Exception;

	boolean addRRMaritalStatus(TrainingType obj) throws Exception;

	boolean updateRRMaritalStatus(TrainingType obj) throws Exception;

	boolean deleteRRMaritalStatus(TrainingType obj) throws Exception;

}
