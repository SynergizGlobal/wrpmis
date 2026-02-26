package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRMaritalStatusDao {

	TrainingType getRRMaritalStatusDetails(TrainingType obj) throws Exception;

	boolean addRRMaritalStatus(TrainingType obj) throws Exception;

	boolean updateRRMaritalStatus(TrainingType obj) throws Exception;

	boolean deleteRRMaritalStatus(TrainingType obj) throws Exception;

}
