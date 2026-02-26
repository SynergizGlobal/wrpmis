package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRTenureStatusDao {

	TrainingType getRRTenureStatusDetails(TrainingType obj) throws Exception;

	boolean addRRTenureStatus(TrainingType obj) throws Exception;

	boolean updateRRTenureStatus(TrainingType obj) throws Exception;

	boolean deleteRRTenureStatus(TrainingType obj) throws Exception;	

}
