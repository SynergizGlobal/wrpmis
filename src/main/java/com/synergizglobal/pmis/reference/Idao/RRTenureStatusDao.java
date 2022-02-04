package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRTenureStatusDao {

	TrainingType getRRTenureStatusDetails(TrainingType obj) throws Exception;

	boolean addRRTenureStatus(TrainingType obj) throws Exception;

	boolean updateRRTenureStatus(TrainingType obj) throws Exception;

	boolean deleteRRTenureStatus(TrainingType obj) throws Exception;	

}
